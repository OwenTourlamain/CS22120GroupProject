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
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


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
public class SpeciesDetailsScreen extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {

	private Record currentRecord;
	private Button mPhotoButton;
	private static final int IMAGE_REQUEST_CODE=100;
	public static final int MEDIA_TYPE_IMAGE = 1;
	protected GoogleApiClient mGoogleApiClient;
	protected static final String TAG = "basic-location-sample";
	protected TextView mLatitudeText;
	protected TextView mLongitudeText;
	private final Button daforButtonD = (Button) findViewById(R.id.daforScaleButtonD);
	final Button daforButtonA = (Button) findViewById(R.id.daforScaleButtonA);
	final Button daforButtonF = (Button) findViewById(R.id.daforScaleButtonF);
	final Button daforButtonO = (Button) findViewById(R.id.daforScaleButtonO);
	final Button daforButtonR = (Button) findViewById(R.id.daforScaleButtonR);
	
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
		
		daforButtonD.setSelected( true ); // set a default
		
		mPhotoButton = (Button)findViewById(R.id.photoButton);
		
		mPhotoButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				TakePicture();
			}
		});
		
		Bundle extras = getIntent().getExtras();
		currentRecord = (Record) extras.getSerializable( "newRecord" );
	
		final EditText nameEntryField = (EditText) findViewById( R.id.nameEntryField );
		
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
				
				currentRecord.setRecorder( nameEntryField.getText().toString() );
				
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
				
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				daforButtonD.setSelected( true );
				daforButtonA.setSelected( false );
				daforButtonF.setSelected( false );
				daforButtonO.setSelected( false );
				daforButtonR.setSelected( false );
				
				currentRecord.setAbundance( 'D' );
			}
		});
		
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				daforButtonD.setSelected( false );
				daforButtonA.setSelected( true );
				daforButtonF.setSelected( false );
				daforButtonO.setSelected( false );
				daforButtonR.setSelected( false );
				
				currentRecord.setAbundance( 'A' );
			}
		});
		
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				daforButtonD.setSelected( false );
				daforButtonA.setSelected( false );
				daforButtonF.setSelected( true );
				daforButtonO.setSelected( false );
				daforButtonR.setSelected( false );
				
				currentRecord.setAbundance( 'F' );
			}
		});
		
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				daforButtonD.setSelected( false );
				daforButtonA.setSelected( false );
				daforButtonF.setSelected( false );
				daforButtonO.setSelected( true );
				daforButtonR.setSelected( false );
				
				currentRecord.setAbundance( 'O' );
			}
		});
		
		daforButtonD.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				daforButtonD.setSelected( false );
				daforButtonA.setSelected( false );
				daforButtonF.setSelected( false );
				daforButtonO.setSelected( false );
				daforButtonR.setSelected( true );
				
				currentRecord.setAbundance( 'R' );
			}
		});
		
		/*
		 * Buttons for Gallery, camera etc need to go here,
		 * same deal as with buttons for DAFOR
		 */
		
	}
	private void TakePicture(){

			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
		// Create the File where the photo should go
		File photoFile = null;
		try {
		photoFile = createImageFile();
		} catch (IOException ex) {
		// Error occurred while creating the File
		
		}
		// Continue only if the File was successfully created
		if (photoFile != null) {
		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
		Uri.fromFile(photoFile));
		startActivityForResult(takePictureIntent, IMAGE_REQUEST_CODE);
		}
		}
	}
		
	protected synchronized void buildGoogleApiClient() {
		mGoogleApiClient = new GoogleApiClient.Builder(this)
		.addConnectionCallbacks(this)
		.addOnConnectionFailedListener((OnConnectionFailedListener) this)
		// add API for gps
		.addApi(LocationServices.API)
		.build();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		//if (!mResolvingError) { // more about this later
		mGoogleApiClient.connect();
	// }
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected())
		{
			mGoogleApiClient.disconnect();
		}
	}
	
	
	
	@Override
	public void onConnected(Bundle connectionHint) {
		Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
		mGoogleApiClient);
		
		if (mLastLocation != null) {
			mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
			mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
		}
	}
	
	
	@Override
	public void onConnectionSuspended( int i ) {
			Log.i(TAG, "Connection suspended");
			mGoogleApiClient.connect();
	}

	@Override
	public void onConnectionFailed(ConnectionResult result)
	{
		Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
	}
}
