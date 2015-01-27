package com.example.PlantsProject;

/**
 * What needs to be done:
 * 1. methods responsible for operating on data
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

public class AddingSpecies extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adding_species);
		//getWindow().getDecorView().setBackgroundColor(Color.parseColor("#F4F4F4"));
		// "AddNew" button
		Button addNewButton = (Button) findViewById(R.id.addNewButton);
		addNewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						SpeciesDetailsScreen.class);
				startActivityForResult(intent, 0);
			}
		});

		// "BSBI" button
		Button bsbiButton = (Button) findViewById(R.id.bsbiButton);
		bsbiButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), BsbiControl.class);
				startActivityForResult(intent, 0);
			}
		});

		// "add species to data" button
		Button continueButton = (Button) findViewById(R.id.addToDataButton);
		continueButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//SINGLE LINE TOAST
				Toast.makeText(getBaseContext(), "Specie added", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(v.getContext(),
						SiteCreationScreen.class);
				startActivityForResult(intent, 0);
			}
		});

		// "home" button
		Button homeButton2 = (Button) findViewById(R.id.homeButton2);
		homeButton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivityForResult(intent, 0);
			}
		});

	}
}