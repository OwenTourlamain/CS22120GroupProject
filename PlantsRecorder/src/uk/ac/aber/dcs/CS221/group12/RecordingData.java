/*
* @(#) RecordingData.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/
package uk.ac.aber.dcs.CS221.group12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


/**
* RecordingData - A class that does something.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class RecordingData extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recording_data);
		ImageView image = (ImageView) findViewById(R.id.sitePhoto);

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
