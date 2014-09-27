package com.techlovejump.flashlight;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
public class MainActivity extends Activity {

	ImageButton Switch_On_OFF;
	
	
	//To detect flash is on or not
	private boolean Light_Flag = false;
	
	private Camera camera;
	
	//If Phone Has Flash Support or not
	private boolean Flash_Support=true;
	
	android.hardware.Camera.Parameters pic;
	@Override
	protected void onStop() {
		
		super.onStop();
		if(camera !=null){

			Flash_Off();
        	camera.release();
}
		
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		if(camera !=null){

			Flash_Off();
        	camera.release();
}
		
	}

	@Override
	protected void onPause() {
		
		super.onPause();
		Flash_On();
      }

	@Override
	protected void onResume() {
		
		super.onPause();
		if(Flash_Support){
		Flash_Off();
		}
      }
 
		
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		Switch_On_OFF = (ImageButton) findViewById(R.id.Btn_Switch);
	
		detectCamera();
		Flash_Support = detectFlash();
	
		if(!Flash_Support){
			
			//No Flash Support ,we will Close the App
			AlertDialog alert  = new AlertDialog.Builder(MainActivity.this).create();
			alert.setTitle("Hardware Error ");
			alert.setMessage("App Will Close Down As your device doesn't seem to support flash :/ ");
			alert.setButton("Ok",new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					 finish();
				}
	
		});
			
		}
	
		try{

			camera = Camera.open();
            pic  = camera.getParameters();
		}catch (RuntimeException e){
			Log.e("CameraError:","Failed To Open");
		}
	   
		Switch_On_OFF.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				if(Light_Flag)
					Flash_Off();
		    	else
					 Flash_On();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	public void detectCamera(){
		
		PackageManager pm = this.getPackageManager();
		
		if(!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
			//No Flash Support ,we will Close the App
			AlertDialog alert  = new AlertDialog.Builder(MainActivity.this).create();
			alert.setTitle("Hardware Error ");
			alert.setMessage("App Will Close Down As your device doesn't seem to support Camera :/ ");
			alert.setButton("Ok",new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					 finish();
				}
	
		});
		}
		
	}
	public boolean detectFlash(){
		
		boolean flash  = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		return flash;
		
	}
	
	
	public void Flash_On(){
		
		if(camera==null)
			 return;
		
		pic.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(pic);
		camera.startPreview();
		Light_Flag = true;
		Switch_On_OFF.setImageResource(R.drawable.off);
		
	}
public void Flash_Off(){
		
		if(camera==null)
			 return;
		
		pic.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(pic);
		camera.startPreview();
		Light_Flag = false;
		Switch_On_OFF.setImageResource(R.drawable.on);
		
	}
}
