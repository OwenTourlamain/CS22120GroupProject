package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.IntentSender.SendIntentException;
//import android.location.Location;
//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
//import android.view.MenuItem;

//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
//import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
//import com.google.android.gms.location.LocationServices;

public class SpeciesCreateActivity extends FragmentActivity //implements ConnectionCallbacks, OnConnectionFailedListener
{
//	// Request code to use when launching the resolution activity
//    private static final int REQUEST_RESOLVE_ERROR = 1001;
//    // Unique tag for the error dialog fragment
//    private static final String DIALOG_ERROR = "dialog_error";
//    // Bool to track whether the app is already resolving an error
//    private boolean mResolvingError = false;
//
//	private GoogleApiClient mGoogleApiClient;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_species_create);
//		buildGoogleApiClient();
//		
//		
//	}
//	
//	 @Override
//	    protected void onStart() {
//	        super.onStart();
//	        if (!mResolvingError) {  // more about this later
//	            mGoogleApiClient.connect();
//	        }
//	    }
//
//	    @Override
//	    protected void onStop() {
//	        mGoogleApiClient.disconnect();
//	        super.onStop();
//	    }
//	
//	protected synchronized void buildGoogleApiClient() {
//	    mGoogleApiClient = new GoogleApiClient.Builder(this)
//	        .addConnectionCallbacks(this)
//	        .addOnConnectionFailedListener(this)
//	        .addApi(LocationServices.API)
//	        .build();
//	}
//	
//	@Override
//    public void onConnected(Bundle connectionHint) {
//		Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                mGoogleApiClient);
//		
//        if (mLastLocation != null) {
//            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//            
//			mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
//        }
//    }
//    
//
//    @Override
//    public void onConnectionSuspended(int cause) {
//        // The connection has been interrupted.
//        // Disable any UI components that depend on Google APIs
//        // until onConnected() is called.
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//    	 if (mResolvingError) {
//             // Already attempting to resolve an error.
//             return;
//         } else if (result.hasResolution()) {
//             try {
//                 mResolvingError = true;
//                 result.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
//             } catch (SendIntentException e) {
//                 // There was an error with the resolution intent. Try again.
//                 mGoogleApiClient.connect();
//             }
//         } else {
//             // Show dialog using GooglePlayServicesUtil.getErrorDialog()
//             showErrorDialog(result.getErrorCode());
//             mResolvingError = true;
//         }
//        
//    }
//    
//    private void showErrorDialog(int errorCode) {
//        // Create a fragment for the error dialog
//        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
//        // Pass the error that should be displayed
//        Bundle args = new Bundle();
//        args.putInt(DIALOG_ERROR, errorCode);
//        dialogFragment.setArguments(args);
//        dialogFragment.show(getSupportFragmentManager(), "errordialog");
//    }
//    
//    /* Called from ErrorDialogFragment when the dialog is dismissed. */
//    public void onDialogDismissed() {
//        mResolvingError = false;
//    }
//
//    /* A fragment to display an error dialog */
//    public static class ErrorDialogFragment extends DialogFragment {
//        public ErrorDialogFragment() { }
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState) {
//            // Get the error code and retrieve the appropriate dialog
//            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
//            return GooglePlayServicesUtil.getErrorDialog(errorCode,
//                    this.getActivity(), REQUEST_RESOLVE_ERROR);
//        }
//
//        @Override
//        public void onDismiss(DialogInterface dialog) {
//            ((MainActivity)getActivity()).onDialogDismissed();
//        }
//
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//	
//	
//	
//    }
}
