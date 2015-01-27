/*
* @(#) SiteCreationScreen.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/
package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import uk.ac.aber.dcs.CS22120.grouptwelve.Record;
import uk.ac.aber.dcs.CS22120.grouptwelve.Site;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


/**
* SiteCreationScreen - A class that does something.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class SiteCreationScreen extends Activity
{
	private Record currentRecord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.site_creation_screen);
		
		Bundle extras = getIntent().getExtras();
		currentRecord = (Record) extras.getSerializable( "newRecord" );
		
		/**
		 * Adding functionality to buttons 
		 */
		
		final EditText siteNameEntry = (EditText) findViewById( R.id.siteNameEntryField );
		final EditText siteComments = (EditText) findViewById( R.id.commentsEntryField );
		
		// "Create" button
		Button createButton = (Button) findViewById( R.id.createNewSiteButton );
		createButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				currentRecord.setSite(
						new Site
						(
							siteNameEntry.getText().toString(),
							siteComments.getText().toString()
							
						) );
				
				Intent intent = new Intent(v.getContext(), AddingSpecies.class);
				intent.putExtra( "newRecord", currentRecord );
				startActivity( intent );
			}
		});
		
		Button useExistingSiteButton = (Button) findViewById( R.id.useExistingSiteButton );
		useExistingSiteButton.setOnClickListener( new OnClickListener()
		{
			public void onClick( View v )
			{
				Intent intent = new Intent( v.getContext(), SiteControl.class );
				intent.putExtra( "newRecord", currentRecord );
				startActivity( intent );
			}
		} );
		
		// "home" button
		Button homeButton1 = (Button) findViewById(R.id.homeButton1);
		homeButton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
