/*
* @(#) SpeciesDetailsScreen.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 


package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.ac.aber.dcs.CS22120.grouptwelve.Record;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
* SpeciesDetailsScreen - A class that does something.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class SpeciesDetailsScreen extends Activity {

	private Record currentRecord;
	private Button mPhotoButton;
	private static final int IMAGE_REQUEST_CODE=100;
	public static final int MEDIA_TYPE_IMAGE = 1;
	protected GoogleApiClient mGoogleApiClient;
	protected static final String TAG = "basic-location-sample";
	protected TextView mLatitudeText;
	protected TextView mLongitudeText;
	
	String mCurrentPhotoPath;
	
	private File createImageFile() throws IOException {
			// Create an image file name using timestamp
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String imageFileName = "JPEG_" + timeStamp + "_";
			File storageDir = Environment.getExternalStoragePublicDirectory(
			Environment.DIRECTORY_PICTURES);
			File image = File.createTempFile(
			imageFileName, /* prefix */
			".jpg", /* suffix */
			storageDir /* directory */
			);
						// Save a file: path for use with ACTION_VIEW intents
			mCurrentPhotoPath = "file:" + image.getAbsolutePath();
			return image;
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.species_details_screen);

		super.onCreate(savedInstanceState);
		// display gps longitude and latitude.
		mLatitudeText = (TextView) findViewById((R.id.latitude_text));
		mLongitudeText = (TextView) findViewById((R.id.longitude_text));
		buildGoogleApiClient();
		
		Bundle extras = getIntent().getExtras();
		currentRecord = (Record) extras.getSerializable( "newRecord" );
	
		EditText nameEntryField = (EditText) findViewById( R.id.nameEntryField );
		EditText commentEntryField = (EditText) findViewById( R.id.commentsEntryField );
		
		
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
				intent.putExtra( "newRecord", currentRecord );
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
		final Button daforButtonD = (Button) findViewById(R.id.daforScaleButtonD);
		final Button daforButtonA = (Button) findViewById(R.id.daforScaleButtonA);
		final Button daforButtonF = (Button) findViewById(R.id.daforScaleButtonF);
		final Button daforButtonO = (Button) findViewById(R.id.daforScaleButtonO);
		final Button daforButtonR = (Button) findViewById(R.id.daforScaleButtonR);
		
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				daforButtonD.setSelected( true );
				daforButtonA.setSelected( false );
				daforButtonF.setSelected( false );
				daforButtonO.setSelected( false );
				daforButtonR.setSelected( false );
			}
		});
		
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				daforButtonD.setSelected( false );
				daforButtonA.setSelected( true );
				daforButtonF.setSelected( false );
				daforButtonO.setSelected( false );
				daforButtonR.setSelected( false );
			}
		});
		
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				daforButtonD.setSelected( false );
				daforButtonA.setSelected( false );
				daforButtonF.setSelected( true );
				daforButtonO.setSelected( false );
				daforButtonR.setSelected( false );
			}
		});
		
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				daforButtonD.setSelected( false );
				daforButtonA.setSelected( false );
				daforButtonF.setSelected( false );
				daforButtonO.setSelected( true );
				daforButtonR.setSelected( false );
			}
		});
		
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				daforButtonD.setSelected( false );
				daforButtonA.setSelected( false );
				daforButtonF.setSelected( false );
				daforButtonO.setSelected( false );
				daforButtonR.setSelected( true );
			}
		});
		
		/*
		 * Buttons for Gallery, camera etc need to go here,
		 * same deal as with buttons for DAFOR
		 */
		
	}

}
