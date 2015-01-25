package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

//This page is where the user enters their name email and phone number




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class DetailsActivity extends Activity {
	private Button mSubmitButton;
	EditText name;
	EditText email;
	EditText phnumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		// input fields
		name = (EditText) findViewById(R.id.Name);
		email = (EditText) findViewById(R.id.Email);
		phnumber = (EditText) findViewById(R.id.Phone);
		//button
		mSubmitButton = (Button)findViewById(R.id.input_submit_button);
		
	mSubmitButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent myIntent = new Intent(v.getContext(),OptionsActivity.class);
            startActivityForResult(myIntent, 0);
			
			
		}});
	
		
		
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
}