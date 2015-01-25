package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

/*
 * This is the main page
 */

import java.sql.SQLException;

import uk.ac.aber.dcs.CS22120.grouptwelve.SpeciesDatabase;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button mDetailsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SpeciesDatabase test = new SpeciesDatabase( this.getApplicationContext() );
		
		try
		{
			test.startSpeciesDatabase();
			
		} catch( SQLException e )
		{
			Log.v( "SpeciesDatabase", "Could not open database!" );
		}
		
		test.closeSpeciesDatabase();
		
		setContentView(R.layout.activity_main);
		mDetailsButton = (Button)findViewById(R.id.details_button);
	
	
	mDetailsButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			Intent myIntent = new Intent(v.getContext(), DetailsActivity.class);
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
