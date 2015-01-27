package com.example.PlantsProject;

/**
 * What needs to be done:
 * 1. saving the data from text fields, creating a user etc
 */

import com.example.plants_project.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DetailsEntryScreen extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_entry_screen);
		//getWindow().getDecorView().setBackgroundColor(Color.parseColor("#F4F4F4"));
		/**
		 * Adding functionality to buttons 
		 */

		// "Continue" button
		Button continueButton = (Button) findViewById(R.id.continueButton01);
		continueButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				//SINGLE LINE TOAST
				Toast.makeText(getBaseContext(), "User data accepted", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(v.getContext(), SiteCreationScreen.class);
				startActivityForResult(intent, 0);
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
}
