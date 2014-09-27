package com.techlovejump.jsonparser;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONRequest {

	public static JSONObject getJSONfromURL(String url) {
		String result = "";
		JSONObject jArray = null;
		 
		// Download JSON data from URL
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);;
			
		} catch (Exception e) {
			Log.e("Network","Error in http connection " + e.toString());
		}
		
		//Convert To JSON Array from string
		try {

			jArray = new JSONObject(result);
		} catch (JSONException e) {
			Log.e("JSONARRAY", "Error parsing data " + e.toString());
		}

		return jArray;
	}

	public static JSONObject getJSONlocally(InputStream is) {
		 String json;
	        try {

	            int size = is.available();

	            byte[] buffer = new byte[size];

	            is.read(buffer);

	            is.close();

	            json = new String(buffer, "UTF-8");


	        } catch (IOException ex) {
	            ex.printStackTrace();
	            return null;
	        }
	        JSONObject json_obj = null;
			try {
				json_obj = new JSONObject(json);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return json_obj;

	
	}
}
