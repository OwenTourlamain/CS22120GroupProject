package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import java.sql.SQLException;

import uk.ac.aber.dcs.CS22120.grouptwelve.SpeciesDatabase;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SpeciesDetailsScreen extends Activity {

	private SpeciesDatabase speciesDb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.species_details_screen);

		try
		{
			speciesDb = new SpeciesDatabase( this.getApplicationContext() );
			speciesDb.startSpeciesDatabase();
		
		} catch( SQLException sqle )
		{
			Log.e( "SpeciesDatabase", "Could not open species database!" );
		}
		
		/**
		 * Adding functionality to buttons
		 */
		// "home" button
		Button homeButton2 = (Button) findViewById(R.id.homeButton3);
		homeButton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				speciesDb.closeSpeciesDatabase();
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		// "Continue" button
		Button addSpecieButton = (Button) findViewById(R.id.addSpecieButton);
		addSpecieButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				speciesDb.closeSpeciesDatabase();
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
				speciesDb.closeSpeciesDatabase();
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
