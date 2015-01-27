package com.example.PlantsProject;

/**
 * What needs to be done: 
 * 1. data in the list pulled from the database
 * 2. positions on the list clickable, adding the BSBI position to the the site data
 */

import java.util.ArrayList;

import com.example.plants_project.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class BsbiControl extends Activity {

	EditText editsearch;								//for the text field on the top to search for positions
	ListView listView;									//used to display all the positions
	private ArrayList<String> bsbiList;					//holds the data from the database 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bsbi_control_view);
		getWindow().getDecorView().setBackgroundColor(Color.parseColor("#F4F4F4"));
		
		editsearch = (EditText)findViewById(R.id.bsbi_search);
		listView = (ListView)findViewById(R.id.bsbi_view);
		
		bsbiList = new ArrayList<String>();
		populateBsbiList();
        
        //the simple_list_item_1 used below is a part of inbuilt xml layouts, can be changed if needed 
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, bsbiList));
        
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
        
        
     // "ADD" button
     		Button bsbiAddButton = (Button) findViewById(R.id.bsbiAddButton);
     		bsbiAddButton.setOnClickListener(new OnClickListener() {
     			public void onClick(View v) {
     				//SINGLE LINE TOAST
    				Toast.makeText(getBaseContext(), "Data added to the site", Toast.LENGTH_SHORT).show();
     				Intent intent = new Intent(v.getContext(), AddingSpecies.class);
     				startActivityForResult(intent, 0);
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
	public void populateBsbiList (){
		bsbiList.add("1st item");
		bsbiList.add("2nd item");
		bsbiList.add("3rd item");
		bsbiList.add("4th item");
		bsbiList.add("5th item");
		bsbiList.add("6th item");
		bsbiList.add("7th item");
	}
}
