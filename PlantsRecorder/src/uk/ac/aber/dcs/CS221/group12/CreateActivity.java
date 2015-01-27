///*
// * This class is used to create a site and add comments, see the activity_create.xml layout file
// */
//
//package uk.ac.aber.dcs.CS221.group12;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//
//
//public class CreateActivity extends Activity {
//	//Progress dialog when loading.
//	private ProgressDialog pDialog1;
//	private Button mAddButton;
//	
//	//create an instance of JSONParser class
//	JSONParser jsonParser = new JSONParser();
//	
//	//Input Fields
//	EditText siteName;
//	EditText comment;
//	
//	/**
//	 * URL to "create_new_site.php page
//	 * 
//	 * CHANGE TO RELEVENT PHP FILE ON SERVER
//	 */
//	
//	private static String url_create_site = "http://cs211group12db.tk/addSite.php
//	
//	//JSON Node names
//	private static final String TAG_SUCCESS = "success";
//	
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_create);
//		
//		//input fields
//		siteName = (EditText) findViewById(R.id.siteNameEntryField);
//		comment = (EditText) findViewById(R.id.commentsEntryField);
//		
//		//button
//		mAddButton = (Button)findViewById(R.id.create_screen_button);
//		
//		// button click event
//		mAddButton.setOnClickListener(new View.OnClickListener() {
//			
//			/**
//			 * when clicked create an instance of the Async thread and execute
//			 */
//			@Override
//			public void onClick(View v) {
//				new CreateNewSite().execute();
//				//Intent myIntent = new Intent(v.getContext(),SpeciesActivity.class);
//	            //startActivityForResult(myIntent, 0);
//			}
//				
//		});
//			
//	}
//	
//	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//
//	
//	/**
//	 * Background Async thread to create a new site
//	 * @author James
//	 */
//	
//	class CreateNewSite extends AsyncTask<String, String, String>{
//		/**
//		 * Before starting thread show progress dialog
//		 */
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//			pDialog1 = new ProgressDialog(CreateActivity.this);
//			pDialog1.setMessage("Creating site...");
//			pDialog1.setIndeterminate(false);
//			pDialog1.setCancelable(true);
//			pDialog1.show();
//		}
//		
//		/**
//		 * Creating a Site
//		 */
//		protected String doInBackground(String... args) {
//			String siteNameStr = siteName.getText().toString();
//			String commentStr = comment.getText().toString();	
//			//Building Parameters
//		
//			List<NameValuePair> siteParams = new ArrayList<NameValuePair>();
//			siteParams.add(new BasicNameValuePair("name", siteNameStr));
//			siteParams.add(new BasicNameValuePair("comment", commentStr));
//			
//			//getting JSON Object from the UL using POST
//			JSONObject json = jsonParser.makeHttpRequest(url_create_site,"POST", siteParams);
//			
//			//create Log
//			Log.d("Create Response", json.toString());
//			
//			//check for success tag
//			try {
//				int success = json.getInt(TAG_SUCCESS);
//				
//				if(success == 1) {
//					//successfully created a new site
//					//go to next screen *can be changed for final version of app*
//					Intent i = new Intent(getApplicationContext(),SpeciesActivity.class);
//					startActivity(i);
//					
//					//close this screen
//					finish();
//				} else {
//					//failed to create a site
//				}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			
//			return null;
//		}
//		
//		/**
//		 * After completing background task, Dismiss the progress dialog
//		 */
//		
//		protected void OnPostExecute(String file_url) {
//			
//			//dismiss the dialog once done
//			pDialog1.dismiss();
//		}
//	}
//}
//		
//	
//
//	
//	
//				
//			
//		
//	
//	
