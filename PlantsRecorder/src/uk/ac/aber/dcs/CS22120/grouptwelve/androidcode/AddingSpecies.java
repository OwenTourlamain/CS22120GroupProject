package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

/**
 * What needs to be done:
 * 1. methods responsible for operating on data
 */

import java.sql.SQLException;

import uk.ac.aber.dcs.CS22120.grouptwelve.Record;
import uk.ac.aber.dcs.CS22120.grouptwelve.Species;
import uk.ac.aber.dcs.CS22120.grouptwelve.SpeciesDatabase;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddingSpecies extends Activity {

	private SpeciesDatabase speciesDb; // provides access to the database
	private Record currentRecord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adding_species);
		
		Bundle extras = getIntent().getExtras();
		currentRecord = (Record) extras.getSerializable( "newRecord" );
		
		speciesDb = new SpeciesDatabase( this.getApplicationContext() );
			
		// "AddNew" button
		Button addNewButton = (Button) findViewById(R.id.addNewButton);
		addNewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), SpeciesDetailsScreen.class);
				intent.putExtra( "newRecord", currentRecord );
				startActivityForResult(intent, 0);
			}
		});

		// "BSBI" button
		Button bsbiButton = (Button) findViewById(R.id.bsbiButton);
		bsbiButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), BsbiControl.class);
				intent.putExtra( "newRecord", currentRecord );
				startActivity( intent );
			}
		});

		// "home" button
		Button homeButton2 = (Button) findViewById(R.id.homeButton2);
		homeButton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivity( intent );
			}
		});
	}
	
	@Override
	public void onActivityResult( int requestCode, int resultCode, Intent data )
	{
		currentRecord.setSpecies( (Species) data.getSerializableExtra( "newSpecies" ) );
		
		try
		{
			speciesDb.startSpeciesDatabase();
		
		} catch( SQLException sqle )
		{
			Log.e( "SpeciesDatabase", "Could not connect to the species database!" );
		}
		
		speciesDb.addRecord( currentRecord );
		
		speciesDb.closeSpeciesDatabase();
	}
}