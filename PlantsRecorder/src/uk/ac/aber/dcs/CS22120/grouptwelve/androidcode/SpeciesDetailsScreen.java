package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * allows the user to create a species record and attach it to the
 * current record
 *
 */
public class SpeciesDetailsScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.species_details_screen);

		/**
		 * Adding functionality to buttons
		 */
		// "home" button
		Button homeButton2 = (Button) findViewById(R.id.homeButton2);
		homeButton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivityForResult(intent, 0);
			}
		});
	}

}
