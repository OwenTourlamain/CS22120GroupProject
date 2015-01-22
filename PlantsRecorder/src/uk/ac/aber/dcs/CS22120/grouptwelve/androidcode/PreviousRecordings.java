package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PreviousRecordings extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.previous_recordings);
		
		/**
		 * Adding functionality to buttons
		 */
		// "home" button
				Button homeButton3 = (Button) findViewById(R.id.homeButton3);
				homeButton3.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent(v.getContext(), MainActivity.class);
						startActivityForResult(intent, 0);
					}
				});
	}
	
}
