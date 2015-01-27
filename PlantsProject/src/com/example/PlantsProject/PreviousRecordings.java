package com.example.PlantsProject;

/**
 * 
 * This method needs additional functionality
 * items from ListView should be clickable and should transport the user
 * to the given recording's data
 * 
 */
import java.util.ArrayList;

import com.example.plants_project.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PreviousRecordings extends Activity{

	ListView listView;									//used to display all the positions
	private ArrayList<String> recordList;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.previous_recordings);
		getWindow().getDecorView().setBackgroundColor(Color.parseColor("#F4F4F4"));
		
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
