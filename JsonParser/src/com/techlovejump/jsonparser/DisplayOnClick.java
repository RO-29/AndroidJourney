package com.techlovejump.jsonparser;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayOnClick extends Activity{
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);

        Intent i = getIntent();
        Users user = (Users)i.getSerializableExtra("UserDetails");
	 
		TextView Id = (TextView) findViewById(R.id.id);
		TextView Country = (TextView) findViewById(R.id.country);
		TextView Name = (TextView) findViewById(R.id.name);

		Id.setText("ID : "+ user.getId());
		Country.setText("Name : "+ user.getName());
		Name.setText("Country : "+ user.getName());
	
	}
	


}
