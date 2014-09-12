package com.technolovejump.xmlparser;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.technolovejump.xmlparser.BookData;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import com.technolovejump.xmlparser.Bookhandler;
import com.technolovejump.xmlparser.xmlRequest;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends Activity {

	TextView xmlOutput;
	EditText xmlInput;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ProgressDialog progress = new ProgressDialog(this);

		setContentView(R.layout.activity_main);
		
		Button parseMyXML = (Button) findViewById(R.id.parse);
        parseMyXML.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	new parseMagic().execute();


            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	class parseMagic extends AsyncTask<String,String,String>{

		
		
		TextView xmlOutput = null;
		ProgressDialog progress = null;

		String response = null;
		@Override
		protected void onPreExecute(){
			xmlOutput =(TextView) MainActivity.this.findViewById(R.id.xmlOutput);
			progress = new ProgressDialog(MainActivity.this);		 
		}
		
		  
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			 xmlInput = (EditText) findViewById(R.id.xmlInput);
		     response = new xmlRequest().XMLUrlHttpRequest(xmlInput.getText().toString());

			String parsedData = "";
	        
	        try {
	 
	            Log.w("AndroidParseXMLActivity", "Start");
	            SAXParserFactory spf = SAXParserFactory.newInstance();
	            SAXParser sp = spf.newSAXParser();
	            XMLReader xr = sp.getXMLReader();
	 
	            Bookhandler XMLHandler = new Bookhandler();
	            xr.setContentHandler(XMLHandler);
	            InputSource inStream = new InputSource();
	            
	            inStream.setCharacterStream(new StringReader(response));
	            
	            xr.parse(inStream);
	            Log.w("response",response);
	            int i=0;
	            ArrayList<BookData> BooksList = XMLHandler.getBooksList();
	            for(;i<BooksList.size();i++){
	                BookData book = BooksList.get(i);
	                parsedData = parsedData + "Book "+(i+1) +"Detail----->\n";
	                parsedData = parsedData + "Book Number: " + book.getBookNumber() + "\n";
	                parsedData = parsedData + "Description: " + book.getDescription() + "\n";
	                parsedData = parsedData + "Price: " + book.getPrice() + "\n";
	                parsedData = parsedData + "Rating: "+ book.getRating() + "\n";
	                
	            }
	            Log.w("i==",String.valueOf(i));
	            Log.w("Data",parsedData);

	            Log.w("ParsingXml and populating", "Done");
	        }
	        catch (Exception e) {
	            Log.w("AndroidParseXMLActivity",e );
	        }
	        
	       return parsedData;
		
		}
		protected void onPostExecute(String ab){
			//progress.dismiss();
			xmlOutput.setMovementMethod(new ScrollingMovementMethod());
            xmlOutput.setText(ab); 
			 
		}

		
	}



}
