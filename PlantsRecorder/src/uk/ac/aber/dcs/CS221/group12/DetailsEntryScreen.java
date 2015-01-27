/*
* @(#) DetailsEntryScreen.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 

package uk.ac.aber.dcs.CS221.group12;

/**
 * What needs to be done:
 * 1. saving the data from text fields, creating a user etc
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


/**
* DetailsEntryScreen - A class that does something.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class DetailsEntryScreen extends Activity
{
	private Record newRecord;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_entry_screen);
		
		newRecord = new Record();
		
		final EditText nameTextField = (EditText) findViewById( R.id.nameEntryField );
		final EditText emailEntryField = (EditText) findViewById( R.id.emailEntryField );
		final EditText phoneEntryField = (EditText) findViewById( R.id.phoneEntryField );
		/**
		 * Adding functionality to buttons 
		 */

		// "Continue" button
		Button continueButton = (Button) findViewById(R.id.continueButton01);
		continueButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				newRecord.setRecorder( nameTextField.getText().toString() );
				newRecord.setEmail( emailEntryField.getText().toString() );
				newRecord.setPhoneNumber( phoneEntryField.getText().toString() );
				
//				Intent intent = new Intent(v.getContext(), SiteCreationScreen.class);
//				intent.putExtra( "newRecord", newRecord );
//				startActivity(intent);
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
//			newRecord = (Record) data.getSerializableExtra( "CurrentRecord" );
		}
	}
}
