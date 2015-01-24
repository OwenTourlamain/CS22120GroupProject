package uk.ac.aber.dcs.CS22120.grouptwelve.tests;

import uk.ac.aber.dcs.CS22120.grouptwelve.Record;
import uk.ac.aber.dcs.CS22120.grouptwelve.Site;
import uk.ac.aber.dcs.CS22120.grouptwelve.Species;
import uk.ac.aber.dcs.CS22120.grouptwelve.SpeciesDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class SpeciesDatabaseTest extends AndroidTestCase
{
	private SpeciesDatabase speciesDb;
	
	@Override
	public void setUp()
	{
		RenamingDelegatingContext context = new RenamingDelegatingContext( getContext(), "test_" );
		
		speciesDb = new SpeciesDatabase( context );
	}
	
	public void testAddEntry()
	{
		Site testSite = new Site( 1, "test site", 1.0, -1.0 );
		Species testSpecie = new Species( 1, "test species", "test comment" );
		Record testRec = new Record( 1, null, null, null, testSite, testSpecie, null, 'D', null, null ); // TODO fill with real data
	}
	
	@Override
	public void tearDown() throws Exception
	{
		
	}
}
