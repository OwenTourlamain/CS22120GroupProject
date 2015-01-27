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
import android.widget.Toast;

public class SpeciesEditScreen extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.species_editing_screen);
		getWindow().getDecorView().setBackgroundColor(Color.parseColor("#F4F4F4"));
		
		/*
		 * BUTTONS
		 */

		// "home" button
		Button homeButton7 = (Button) findViewById(R.id.homeButton7);
		homeButton7.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		// "finish editing" button
		Button homeButton6 = (Button) findViewById(R.id.homeButton6);
		homeButton6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//SINGLE LINE TOAST
				Toast.makeText(getBaseContext(), "Changes saved", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(v.getContext(), EditingData.class);
				startActivityForResult(intent, 0);
			}
		});

	}
}
