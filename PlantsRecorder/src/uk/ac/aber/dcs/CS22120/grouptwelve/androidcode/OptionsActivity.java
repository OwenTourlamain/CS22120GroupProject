package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

/*
 * provide the user with an option to create a new recording or edit a previous recording
 * 
 * 
 */




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;



public class OptionsActivity extends Activity {
	
	private Button msubmitbutton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		msubmitbutton = (Button)findViewById(R.id.details_button);
		
		
	
		
	
	
		
		
		
	
	}
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radio_create:
	            if (checked){
	            	Intent myIntent = new Intent(view.getContext(), CreateActivity.class);
		            startActivityForResult(myIntent, 0);
	            }
	                
	            break;
	        case R.id.radio_edit:
	            if (checked){
	            	Intent myIntent = new Intent(view.getContext(), EditActivity.class);
		            startActivityForResult(myIntent, 0);
	            }
	               
	            break;
	    }
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