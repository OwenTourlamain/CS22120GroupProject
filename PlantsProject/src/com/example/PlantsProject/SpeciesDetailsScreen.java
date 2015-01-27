package com.example.PlantsProject;

import com.example.plants_project.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SpeciesDetailsScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.species_details_screen);
		//getWindow().getDecorView().setBackgroundColor(Color.parseColor("#F4F4F4"));
		
		/**
		 * Adding functionality to buttons
		 */
		// "home" button
		Button homeButton2 = (Button) findViewById(R.id.homeButton3);
		homeButton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		// "add specie" button
		Button addSpecieButton = (Button) findViewById(R.id.addSpecieButton);
		addSpecieButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//SINGLE LINE TOAST
				Toast.makeText(getBaseContext(), "Specie added", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(v.getContext(), AddingSpecies.class);
				startActivityForResult(intent, 0);
			}
		});

		/*
		 * BUTTONS FOR DAFOR SCALE 
		 * need finishing, adding functionality 
		 * when pressed need to change the DAFOR scale rating of the given plant
		 * (code below does something else, it's just a placeholder copied from
		 * buttons used to move between activities)
		 */
		
		// "DAFOR D" button
		Button daforButtonD = (Button) findViewById(R.id.daforScaleButtonD);
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//SINGLE LINE TOAST
				Toast.makeText(getBaseContext(), "DAFOR scale - D", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(v.getContext(), BsbiControl.class);
				startActivityForResult(intent, 0);
			}
		});
		
		/*
		 * Buttons for Gallery, camera etc need to go here,
		 * same deal as with buttons for DAFOR
		 */

	}

}
