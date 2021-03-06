/*
* @(#) RecordingDataSubmitScreen.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 
package uk.ac.aber.dcs.CS221.group12;

import java.sql.SQLException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
* RecordingDataSubmitScreen - A class that does something.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class RecordingDataSubmitScreen extends Activity {

	private SpeciesDatabase speciesDb;
	private Record currentRecord;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recording_data);
		ImageView image = (ImageView) findViewById(R.id.sitePhoto);
		
		speciesDb = new SpeciesDatabase( this.getApplicationContext() );

		Bundle extras = getIntent().getExtras();
		currentRecord = (Record) extras.getSerializable( "newRecord" );
		
		/*
		 * BUTTONS
		 */

		// "home" button
		Button homeButton5 = (Button) findViewById(R.id.homeButton5);
		homeButton5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivity( intent );
			}
		});

		// "edit" button
		Button editButton = (Button) findViewById(R.id.submitButton);
		editButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		// "species list" button
		Button speciesListButton = (Button) findViewById(R.id.listOfSpecimensButton);
		speciesListButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), SpeciesSelect.class);
				try
				{
					speciesDb.startSpeciesDatabase();
                    speciesDb.addRecord( currentRecord );
                    speciesDb.closeSpeciesDatabase();
                    
                    AlertDialog.Builder submittedSuccesfullyDialog = new AlertDialog.Builder( getApplicationContext() );
    				submittedSuccesfullyDialog.setMessage( "Succesfully Submitted!" );
    				
    				submittedSuccesfullyDialog.setPositiveButton( "Okay",
    						new DialogInterface.OnClickListener() {
    							
    							@Override
    							public void onClick(DialogInterface dialog, int which)
    							{
    								dialog.cancel();
    								
    								Intent intent = new Intent( getApplicationContext(), MainActivity.class );
    								startActivity( intent );
    								startActivityForResult(intent, 0);
    							}
    						});

				} catch( SQLException sqle )
				{
					Log.e( "SpeciesDatabase", "Could not connect to species database!" );
					
					AlertDialog.Builder submittedUnsuccesfullyDialog = new AlertDialog.Builder( getApplicationContext() );
    				submittedUnsuccesfullyDialog.setMessage( "Internal error, could not submit!" );
    				
    				submittedUnsuccesfullyDialog.setPositiveButton( "Okay",
    						new DialogInterface.OnClickListener() {
    							
    							@Override
    							public void onClick(DialogInterface dialog, int which)
    							{
    								dialog.cancel();
    								
    								Intent intent = new Intent( getApplicationContext(), MainActivity.class );
    								startActivity( intent );
    								startActivityForResult(intent, 0);
    							}
    						});
				}
			}
		});
	}
}
