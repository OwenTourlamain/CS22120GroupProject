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

public class SiteCreationScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.site_creation_screen);
		getWindow().getDecorView().setBackgroundColor(Color.parseColor("#F4F4F4"));
		
		/**
		 * Adding functionality to buttons 
		 */
		
		// "Create" button
		Button createButton = (Button) findViewById(R.id.createButton);
		createButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//SINGLE LINE TOAST
				Toast.makeText(getBaseContext(), "New site created", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(v.getContext(), AddingSpecies.class);
				startActivityForResult(intent, 0);
			}
		});
		
		// "home" button
		Button homeButton1 = (Button) findViewById(R.id.homeButton1);
		homeButton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivityForResult(intent, 0);
			}
		});
	}
	
}
