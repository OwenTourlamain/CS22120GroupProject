package com.example.PlantsProject;

import com.example.plants_project.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class RecordingData extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recording_data);
		ImageView image = (ImageView) findViewById(R.id.sitePhoto);
		getWindow().getDecorView().setBackgroundColor(Color.parseColor("#F4F4F4"));
		
		/*
		 * BUTTONS
		 */

		// "home" button
		Button homeButton5 = (Button) findViewById(R.id.homeButton5);
		homeButton5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		// "edit" button
		Button editButton = (Button) findViewById(R.id.editButton);
		editButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), EditingData.class);
				startActivityForResult(intent, 0);
			}
		});

		// "species list" button
		Button speciesListButton = (Button) findViewById(R.id.listOfSpecimensButton);
		speciesListButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), SpeciesSelect.class);
				startActivityForResult(intent, 0);
			}
		});

	}

}
