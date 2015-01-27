package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import java.sql.SQLException;
import java.util.ArrayList;

import uk.ac.aber.dcs.CS22120.grouptwelve.Record;
import uk.ac.aber.dcs.CS22120.grouptwelve.SpeciesDatabase;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class SiteControl extends Activity {

	EditText editsearch;								//for the text field on the top to search for positions
	ListView listView;									//used to display all the positions
	private ArrayList<String> siteList;				//holds the data from the database 
	private SpeciesDatabase speciesDb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.site_select);
		
		try
		{
			speciesDb = new SpeciesDatabase( this.getApplicationContext() );
			speciesDb.startSpeciesDatabase();
			
		} catch( SQLException sqle )
		{
			Log.e( "SpeciesDatabase", "Could not connect to species database!" );
		}
		
		editsearch = (EditText)findViewById(R.id.siteSearch);
		listView = (ListView)findViewById(R.id.siteView);
		
		siteList = new ArrayList<String>();
		populatesiteList();
        
        //the simple_list_item_1 used below is a part of inbuilt xml layouts, can be changed if needed 
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, siteList));
        
        //adding the text watcher and functions it needs to perform
        editsearch.addTextChangedListener(new TextWatcher() {
			//Event when changed word on EditTex
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
					ArrayList<String> temp = new ArrayList<String>();
					int textlength = editsearch.getText().length();
					temp.clear();
					for (int i = 0; i < siteList.size(); i++)
					{
						if (textlength <= siteList.get(i).length())
						{
							if(editsearch.getText().toString().equalsIgnoreCase(
									(String)
									siteList.get(i).subSequence(0,
											textlength)))
							{
								temp.add(siteList.get(i));
							}
						}
					}
					listView.setAdapter(new ArrayAdapter<String>(SiteControl.this,android.R.layout.simple_list_item_1, temp));
				}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
        
	
}
	public void populatesiteList ()
	{
		for( Record rec : speciesDb.getRecordList() )
			siteList.add( rec.getSite().getName() );
	}
}
