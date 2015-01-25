package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * this android class allows the user to create a site and attach it to the record
 *
 */
public class SiteCreationScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.site_creation_screen);
		
		/**
		 * Adding functionality to buttons 
		 */
		
		// "Create" button
		Button createButton = (Button) findViewById(R.id.createButton);
		createButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), SpeciesDetailsScreen.class);
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
