package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import uk.ac.aber.dcs.CS22120.grouptwelve.Record;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SpeciesDetailsScreen extends Activity {

	private Record currentRecord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.species_details_screen);

		Bundle extras = getIntent().getExtras();
		currentRecord = (Record) extras.getSerializable( "newRecord" );
		
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

		// "Continue" button
		Button addSpecieButton = (Button) findViewById(R.id.addSpecieButton);
		addSpecieButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), RecordingDataSubmitScreen.class);
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
