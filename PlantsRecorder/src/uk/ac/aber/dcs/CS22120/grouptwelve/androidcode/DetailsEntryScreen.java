package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DetailsEntryScreen extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_entry_screen);

		/**
		 * Adding functionality to buttons 
		 */

		// "Continue" button
		Button continueButton = (Button) findViewById(R.id.continueButton01);
		continueButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), SiteCreationScreen.class);
				startActivityForResult(intent, 0);
			}
		});
		
		// "Previous recordings" button
		Button previousRecButton = (Button) findViewById(R.id.previousRecButton);
		previousRecButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), PreviousRecordings.class);
				startActivityForResult(intent, 0);
			}
		});
		
	}
}
