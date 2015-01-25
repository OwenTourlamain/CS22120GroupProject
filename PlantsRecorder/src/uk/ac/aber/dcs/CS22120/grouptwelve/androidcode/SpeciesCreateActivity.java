package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

// This class is used to create a species

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

public class SpeciesCreateActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener   {
	
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
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.UK ).format(new Date());
	    String imageFileName = "JPEG_" + timeStamp + "_";
	    File storageDir = Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES);
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".jpg",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
	    return image;
	}
	
		
	
	
	

	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_species_create);
		// display gps longitude and latitude.
		mLatitudeText = (TextView) findViewById((R.id.latitude_text));
        mLongitudeText = (TextView) findViewById((R.id.longitude_text));
		buildGoogleApiClient();
		
		    
		    mPhotoButton = (Button)findViewById(R.id.photo_button);
			
			
			mPhotoButton.setOnClickListener(new View.OnClickListener() {
				
				
				public void onClick(View v) {
					TakePicture();
				}
			});
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
	        //if (!mResolvingError) {  // more about this later
	            mGoogleApiClient.connect();
	       // }
	    }

	    @Override
	    protected void onStop() {
	        super.onStop();
	        if (mGoogleApiClient.isConnected()) {
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
    public void onConnectionSuspended(int i) {
    	Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
    	Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    
    
	}
	
	
	
    }

