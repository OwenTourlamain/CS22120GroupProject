/*
* @(#) PreviousRecordings.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 

package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

/**
 * 
 * This method needs additional functionality
 * items from ListView should be clickable and should transport the user
 * to the given recording's data
 * 
 */
import java.sql.SQLException;
import java.util.ArrayList;

import uk.ac.aber.dcs.CS22120.grouptwelve.SpeciesDatabase;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
* PreviousRecordings - A class that does something.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class PreviousRecordings extends Activity{

	ListView listView;									//used to display all the positions
	private ArrayList<String> recordList;
	private SpeciesDatabase speciesDb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.previous_recordings);
		
		try
		{
			speciesDb = new SpeciesDatabase( this.getApplicationContext() );
			speciesDb.startSpeciesDatabase();
		
		} catch( SQLException sqle )
		{
			Log.e( "SpeciesDatabase", "Could not open species database!" );
		}
		
		listView = (ListView)findViewById(R.id.recordView);
		
		recordList=new ArrayList<String>();
		populateRecordList();
		
		//the simple_list_item_1 used below is a part of inbuilt xml layouts, can be changed if needed 
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, recordList));
        
		
		/**
		 * Adding functionality to buttons
		 */
		// "home" button
				Button homeButton3 = (Button) findViewById(R.id.homeButton4);
				homeButton3.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent(v.getContext(), MainActivity.class);
						startActivityForResult(intent, 0);
					}
				});
				
	}
	
	// this method needs to pull the data from the data base
	public void populateRecordList() {
		recordList.add("position 1");
		recordList.add("position 2");
		recordList.add("position 3");
		recordList.add("position 4");
		recordList.add("position 5");
		recordList.add("position 6");
		recordList.add("position 7");
		recordList.add("position 8");
		recordList.add("position 9");
		recordList.add("position 10");
	}
	
}
