/*
* @(#) BsbiControl.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 

package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

/**
 * What needs to be done: 
 * 1. data in the list pulled from the database
 * 2. positions on the list clickable, adding the BSBI position to the the site data
 */
 
 

/**
* BsbiControl - A class that does something.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 

import java.sql.SQLException;
import java.util.ArrayList;

import uk.ac.aber.dcs.CS22120.grouptwelve.Record;
import uk.ac.aber.dcs.CS22120.grouptwelve.SpeciesDatabase;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class BsbiControl extends Activity {

	EditText editsearch;								//for the text field on the top to search for positions
	ListView listView;									//used to display all the positions
	private ArrayList<String> bsbiList;					//holds the data from the database
	private SpeciesDatabase speciesDb;
	private Record currentRecord;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bsbi_control_view);
		
		
		editsearch = (EditText)findViewById(R.id.bsbi_search);
		listView = (ListView)findViewById(R.id.bsbi_view);
		
		bsbiList = new ArrayList<String>();
		populateBsbiList();
        
        //the simple_list_item_1 used below is a part of inbuilt xml layouts, can be changed if needed 
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, bsbiList));
        listView.setOnItemClickListener( new OnItemClickListener()
        {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = new Intent( view.getContext(), RecordingDataSubmitScreen.class );
				
				
				intent.putExtra( "newRecord", currentRecord );
			}
        	
        } );
        
        //adding the text watcher and functions it needs to perform
        editsearch.addTextChangedListener(new TextWatcher() {
			//Event when changed word on EditTex
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
					ArrayList<String> temp = new ArrayList<String>();
					int textlength = editsearch.getText().length();
					temp.clear();
					for (int i = 0; i < bsbiList.size(); i++)
					{
						if (textlength <= bsbiList.get(i).length())
						{
							if(editsearch.getText().toString().equalsIgnoreCase(
									(String)
									bsbiList.get(i).subSequence(0,
											textlength)))
							{
								temp.add(bsbiList.get(i));
							}
						}
					}
					listView.setAdapter(new ArrayAdapter<String>(BsbiControl.this,android.R.layout.simple_list_item_1, temp));
				}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * This method adds all the positions to the bsbiList
	 * data needs to be pulled from the database
	 */
	public void populateBsbiList ()
	{
		try
		{
			speciesDb = new SpeciesDatabase( this.getApplicationContext() );
			speciesDb.startSpeciesDatabase();
			
		} catch( SQLException sqle )
		{
			Log.e( "SpeciesDatabase", "Could not connect to species database!" );
		}
		
		for( Record rec : speciesDb.getRecordList() )
		{
			bsbiList.add( rec.getSpecies().getID() + ": " + rec.getSpecies().getName() );
		}
		
		speciesDb.closeSpeciesDatabase();
	}
}
