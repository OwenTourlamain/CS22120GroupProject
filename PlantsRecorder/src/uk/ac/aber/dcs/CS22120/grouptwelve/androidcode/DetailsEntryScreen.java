package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

/**
 * What needs to be done:
 * 1. saving the data from text fields, creating a user etc
 */

import uk.ac.aber.dcs.CS22120.grouptwelve.Record;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DetailsEntryScreen extends Activity
{
	private Record newRecord;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_entry_screen);
		
		/**
		 * Adding functionality to buttons 
		 */

		// "Continue" button
		Button continueButton = (Button) findViewById(R.id.continueButton01);
		continueButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), SiteCreationScreen.class);
				intent.putExtra( "CurrentRecord", newRecord );
				startActivity(intent);
			}
		});
		
		// "Previous recordings" button
		Button previousRecButton = (Button) findViewById(R.id.previousRecButton);
		previousRecButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), PreviousRecordings.class);
				startActivityForResult(intent, 0);
			}
		});
		
	}
	
	@Override
	public void onActivityResult( int requestCode, int resultCode, Intent data )
	{
		if( resultCode == Activity.RESULT_CANCELED )
			Log.e("DetailsCreation", "Result was canceled" );
	
		else
		{
			newRecord = (Record) data.getSerializableExtra( "CurrentRecord" );
		}
	}
}
