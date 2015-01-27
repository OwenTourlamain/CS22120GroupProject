/*
* @(#) SiteControl.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 
package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
* SiteControl - A class that does something.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class SiteControl extends Activity {

	EditText editsearch;								//for the text field on the top to search for positions
	ListView listView;									//used to display all the positions
	private ArrayList<String> siteList;				//holds the data from the database 
	private SpeciesDatabase speciesDb;
	private Record currentRecord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.site_select);
		
        speciesDb = new SpeciesDatabase( this.getBaseContext() );
		
		Bundle extras = getIntent().getExtras();
		currentRecord = (Record) extras.getSerializable( "newRecord" );
        
		editsearch = (EditText)findViewById(R.id.siteSearch);
		listView = (ListView)findViewById(R.id.siteView);
		
		siteList = new ArrayList<String>();
		populateSiteList();
        
        //the simple_list_item_1 used below is a part of inbuilt xml layouts, can be changed if needed 
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, siteList));
        listView.setOnItemClickListener( new OnItemClickListener()
        {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = new Intent( view.getContext(), AddingSpecies.class );
				intent.putExtra( "newRecord", currentRecord );
				startActivity( intent );
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
					for (int i = 0; i < siteList.size(); i++)
					{
						if (textlength <= siteList.get(i).length())
						{
							if(editsearch.getText().toString().equalsIgnoreCase(
									(String)
									siteList.get(i).subSequence(0,
											textlength)))
							{
								temp.add(siteList.get(i));
							}
						}
					}
					listView.setAdapter(new ArrayAdapter<String>(SiteControl.this,android.R.layout.simple_list_item_1, temp));
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
	public void populateSiteList ()
	{
		try
		{
			speciesDb.startSpeciesDatabase();
			Log.v( "SpeciesDatabase", "Started speciesdatabase" );
			
		} catch( SQLException sqle )
		{
			Log.e( "SpeciesDatabase", "Could not connect to the database!" );
		}
		
		for( Record rec : speciesDb.getRecordList() )
			siteList.add( rec.getSite().getName() );
		
		speciesDb.closeSpeciesDatabase();
	}
}
