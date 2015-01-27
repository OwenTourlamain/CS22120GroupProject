package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import uk.ac.aber.dcs.CS22120.grouptwelve.Record;
import uk.ac.aber.dcs.CS22120.grouptwelve.Site;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SiteCreationScreen extends Activity
{
	private Record currentRecord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.site_creation_screen);
		
		Bundle extras = getIntent().getExtras();
		currentRecord = (Record) extras.getSerializable( "newRecord" );
		
		/**
		 * Adding functionality to buttons 
		 */
		
		final EditText siteNameEntry = (EditText) findViewById( R.id.siteNameEntryField );
		final EditText siteComments = (EditText) findViewById( R.id.commentsEntryField );
		
		// "Create" button
		Button createButton = (Button) findViewById(R.id.createNewSiteButton);
		createButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				
				Log.v( "Validity", "Text is " + validText( siteNameEntry.getText().toString() ) );
				
				if( validText( siteNameEntry.getText().toString() ) && validText( siteComments.getText().toString() ) )
				{
					currentRecord.setSite(
							new Site
							(
								siteNameEntry.getText().toString(),
								siteComments.getText().toString()
							) );
					
					Intent intent = new Intent(v.getContext(), AddingSpecies.class);
					intent.putExtra( "newRecord", currentRecord );
					startActivity( intent );
				
				} else
				{
					if( validText( siteNameEntry.getText().toString() ) )
					{
						siteNameEntry.setError( "Invalid text" );
						Log.v( "InputStuff", "invalid text" );
					}
						
					
					if( validText( siteComments.getText().toString() ) )
					{
						siteComments.setError( "Invalid text" );
						Log.v( "InputStuff", "invalid text" );
					}
				}
			}
		});
		
		Button useExistingSiteButton = (Button) findViewById( R.id.use_existing_site_button );
		useExistingSiteButton.setOnClickListener( new OnClickListener()
		{
			public void onClick( View v )
			{
				Intent intent = new Intent( v.getContext(), SiteControl.class );
				intent.putExtra( "newRecord", currentRecord );
				startActivity( intent );
			}
		} );
		
		// "home" button
		Button homeButton1 = (Button) findViewById(R.id.homeButton1);
		homeButton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private boolean validText( String s )
	{
		if( s.length() == 0 ) return false;
		
		return s.matches( "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$" );
	}
}