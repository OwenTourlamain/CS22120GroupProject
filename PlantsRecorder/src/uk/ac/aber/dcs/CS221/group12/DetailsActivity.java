package uk.ac.aber.dcs.CS221.group12;

//This page is where the user enters their name email and phone number
//Now Adds data to database



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;



/**
 * This page is where the user enters their name email and phone number
 * Now includes code to add to database, works with my test database
 * code may need to be changed a bit to fit in with database set up by Owen.
 * @author James
 *
 */
public class DetailsActivity extends Activity {
	//Progress Dialog when loading.
	private ProgressDialog pDialog;
	private Button mSubmitButton;
	
	//create an instance of JSONParser class
	JSONParser jsonParser = new JSONParser();
	
	//Input fields
	EditText name;
	EditText email;
	EditText phnumber;
	EditText site;
	EditText species;
	EditText time;
	EditText lat;
	EditText lng;
	//EditText abundance;
//	EditText img1;
	//EditText img2;
	EditText comment;
	
	/**url to "create_new_person.php" page 
	*
	* CHANGE TO RELEVENT PHP FILE ON SERVER
	*/
	private static String url_create_person = "http://cs211group12db.tk/addRecord.php";
	
	//JSON Node names
	private static final String TAG_SUCCESS = "success";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		// input fields
		name = (EditText) findViewById(R.id.nameEntryField);
		email = (EditText) findViewById(R.id.emailEntryField);
		phnumber = (EditText) findViewById(R.id.phoneEntryField);
		site = (EditText) findViewById(R.id.siteNameEntryField);
		species = (EditText) findViewById(R.id.nameEntryField);
		lat = (EditText) findViewById(R.id.getLatitude());
		lng = (EditText) findViewById(R.id.getLongitude());
		//abundance = (EditText) findViewById(R.id.??????);
		//img1 = (EditText) findViewById(R.id.sitePhoto);
		//img2 = (EditText) findViewById(R.id.sitePhoto);
		comment = (EditText) findViewById(R.id.commentsEntryField);
		
		//button
		mSubmitButton = (Button)findViewById(R.id.input_submit_button);
		
		// button click event
	    mSubmitButton.setOnClickListener(new View.OnClickListener() {
		
	    	
		/* 
		 * when clicked create an instance of the Async thread and execute.
		 */
		@Override
		public void onClick(View v) {
			new CreateNewPerson().execute();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Background Async task to create a new person
	 * @author James
	 *
	 */
	
	class CreateNewPerson extends AsyncTask<String, String, String>{
		
		/**
		 * Before starting thread show progress dialog
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(DetailsActivity.this);
			pDialog.setMessage("Creating Person...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();		
		}
		
		/**
		 * Creating a person
		 */
		protected String doInBackground(String... args) {
			String nameStr = name.getText().toString();
			String emailStr = email.getText().toString();
			String phnumberStr = phnumber.getText().toString();
			String siteStr = site.getText().toString();
			String speciesStr = species.getText().toString();
			String timeStr = time.getTime().toString();
			String latStr = lat.getText().toString();
			String longStr = long.getText().toString();
			//String abundanceStr = abundance.getText().toString();
			//String img1 
			//String img2
			String commentStr = comment.getText().toString();
			
			//Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", nameStr));
			params.add(new BasicNameValuePair("email", emailStr));
			params.add(new BasicNameValuePair("phnumber", phnumberStr));
			params.add(new BasicNameValuePair("site", siteStr));
			params.add(new BasicNameValuePair("species", speciesStr));
			params.add(new BasicNameValuePair("time", timeStr));
			params.add(new BasicNameValuePair("lat", latStr));
			params.add(new BasicNameValuePair("long", longStr)); 
			//params.add(new BasicNameValuePair("abundance", abundanceStr)); 
			//params.add(new BasicNameValuePair("img1", img1Str)); 
			//params.add(new BasicNameValuePair("img2", img2Str));
			params.add(new BasicNameValuePair("comment", commentStr));
			
			//getting JSON Object from the URL using POST 
			JSONObject json = jsonParser.makeHttpRequest(url_create_person,"POST", params);
			
			//create Log
			Log.d("Create Response", json.toString());
			
			//check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);
				
				if(success == 1) {
					//successfully created a new person
					//go to next screen *can be changed for final version of app*
					Intent i = new Intent(getApplicationContext(), OptionsActivity.class);
					startActivity(i);
					
					//close this screen
					finish();
				} else {
					//failed to create a person
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;
		}
	
		/**
		 * After completing background task, Dismiss the progress dialog
		 * 
		 */
		
		protected void OnPostExecute(String file_url) {
			//dismiss the dialog once done
			pDialog.dismiss();
			
		}
	}
	public void getTime(){
		Calendar c = Calendar.getInstance(); 
		int seconds = c.get(Calendar.SECOND);
		int minutes = c.get(Calander.MINUTE);
		int hour = c.get(Calander.HOUR);
		
		system.out.println(hours.toString() + minutes.toString + seconds.toString());
	}
	
	
	
}













	
			
		
	
	
	
	
	
	

	


