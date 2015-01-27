package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SpecimenEditScreen extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.specimen_editing_screen);

		/*
		 * BUTTONS
		 */

		// "home" button
		Button homeButton7 = (Button) findViewById(R.id.homeButton7);
		homeButton7.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivityForResult(intent, 0);
			}
		});

		// "finish editing" button
		Button homeButton6 = (Button) findViewById(R.id.homeButton6);
		homeButton6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), EditingData.class);
				startActivityForResult(intent, 0);
			}
		});

	}
}
