/*
* @(#) SpeciesSelect.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/
package uk.ac.aber.dcs.CS221.group12;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


/**
* SpeciesSelect - A class that does something.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class SpeciesSelect extends Activity {

	EditText editsearch;								//for the text field on the top to search for positions
	ListView listView;									//used to display all the positions
	private ArrayList<String> speciesList;				//holds the data from the database 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.species_select);
		
		
		editsearch = (EditText)findViewById(R.id.speciesSearch);
		listView = (ListView)findViewById(R.id.speciesView);
		
		speciesList = new ArrayList<String>();
		populateSpeciesList();
        
        //the simple_list_item_1 used below is a part of inbuilt xml layouts, can be changed if needed 
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, speciesList));
        
        //adding the text watcher and functions it needs to perform
        editsearch.addTextChangedListener(new TextWatcher() {
			//Event when changed word on EditTex
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
					ArrayList<String> temp = new ArrayList<String>();
					int textlength = editsearch.getText().length();
					temp.clear();
					for (int i = 0; i < speciesList.size(); i++)
					{
						if (textlength <= speciesList.get(i).length())
						{
							if(editsearch.getText().toString().equalsIgnoreCase(
									(String)
									speciesList.get(i).subSequence(0,
											textlength)))
							{
								temp.add(speciesList.get(i));
							}
						}
					}
					listView.setAdapter(new ArrayAdapter<String>(SpeciesSelect.this,android.R.layout.simple_list_item_1, temp));
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
	public void populateSpeciesList () {
		speciesList.add("position 1");
		speciesList.add("position 2");
		speciesList.add("position 3");
		speciesList.add("position 4");
		speciesList.add("position 5");
		speciesList.add("position 6");
		speciesList.add("position 7");
		
	}
}
