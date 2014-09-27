package com.techlovejump.jsonparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import com.techlovejump.jsonparser.Users;
import com.techlovejump.jsonparser.JSONRequest;
public class MainActivity extends Activity {
	
	
	JSONObject jsonobject;
	JSONArray jsonarray;
	ProgressDialog mProgressDialog;
	ArrayList<String> user_listview_string;
	ArrayList<Users> users;
	ArrayAdapter<String> listAdapter ;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		users = new ArrayList<Users>();
		user_listview_string = new ArrayList<String>();

	    ListView list;

		try {
			
			/*
			We are opening our json file located in assets folder in and passing it in inputstream which is then 
			passed on to our getJSONLocally function defined in JSONRequest.java
		   */
			InputStream is = getAssets().open("users.json");
			jsonobject = JSONRequest.getJSONlocally(is);

		  } catch (IOException e2) {
   		   Log.e("Error JSON", ""+e2.getMessage());
  	 }
		

		int i=0;

		try {
			// Locate the NodeList name
			jsonarray = jsonobject.getJSONArray("Users");
			for (i = 0; i<jsonarray.length(); i++) {
				jsonobject = jsonarray.getJSONObject(i);
			
				if(jsonobject!=null){ 
				Users user = new Users();
				user.setId(jsonobject.optString("id"));
				user.setCountry(jsonobject.optString("country"));
				user.setName(jsonobject.optString("name"));
				users.add(user);
				
				user_listview_string.add(jsonobject.optString("name"));
                
				}
			}
		} catch (Exception e) {
			Log.e("Error JSONLIST",i+":"+jsonarray.length()+""+user_listview_string+e.getMessage());
		}
			
		  list = (ListView) findViewById( R.id.list );
   	      listAdapter = new ArrayAdapter<String>(this, R.layout.listtext, user_listview_string);  
   	      list.setAdapter(listAdapter); 
   	      
    	list.setOnItemClickListener(new OnItemClickListener(){

    		@Override
    		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
    				long id) {
    			
      			 Intent i = new Intent();
   				 i.setClass(MainActivity.this, DisplayOnClick.class);
   				 i.putExtra("UserDetails", users.get(position));
    		     startActivity(i);
    		     
    		     }
       	
        });
   }
}