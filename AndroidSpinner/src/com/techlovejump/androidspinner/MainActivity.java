package com.techlovejump.androidspinner;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.techlovejump.androidspinner.Users;
public class MainActivity extends Activity {
	

	ArrayList<String> user_spinner_string;
	ArrayList<Users> users;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		users = new ArrayList<Users>();
		
		user_spinner_string = new ArrayList<String>();
		
 		Users user = new Users();
		user.setId("1");
		user.setName("rohit");
		
		users.add(user);
		
		user_spinner_string.add("rohit");

		user = new Users();
		user.setId("2");
		user.setName("Vaibhav");
		users.add(user);
		
		user_spinner_string.add("Vaibhav");

		user = new Users();
		user.setId("3");
		user.setName("vivek");
		users.add(user);
		
		user_spinner_string.add("vivek");
		
		user = new Users();
		user.setId("4");
		user.setName("Shubham");
		users.add(user);
		
		user_spinner_string.add("Shubham");

		//
		initSpinner();
	
	}

	public void initSpinner(){
		
		// Locate the spinner in activity_main.xml
					Spinner mySpinner = (Spinner) findViewById(R.id.my_spinner);
					
					// Spinner adapter
mySpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,user_spinner_string));

					// Spinner on item click listener
					
					mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

								@Override
								public void onItemSelected(AdapterView<?> arg0,
										View arg1, int position, long arg3) {
									// TODO Auto-generated method stub
									// Locate the textview in activity_main.xml
									TextView txtid = (TextView) findViewById(R.id.id);
									TextView txtname = (TextView) findViewById(R.id.name);
									
									// Set the text followed by the position 
									txtid.setText("ID : "
											+ users.get(position).getId());
									txtname.setText("Name : "
											+ users.get(position).getName());
								}

								 
								@Override
								public void onNothingSelected(AdapterView<?> arg0) {
									// TODO Auto-generated method stub

								}
							});

		
	}
}
