package uk.ac.aber.dcs.CS22120.grouptwelve.tests;

import java.sql.SQLException;
import java.util.Date;

import android.test.InstrumentationTestCase;
import uk.ac.aber.dcs.CS22120.grouptwelve.Record;
import uk.ac.aber.dcs.CS22120.grouptwelve.Site;
import uk.ac.aber.dcs.CS22120.grouptwelve.Species;
import uk.ac.aber.dcs.CS22120.grouptwelve.SpeciesDatabase;

public class SpeciesDatabaseTest extends InstrumentationTestCase
{
	private SpeciesDatabase speciesDb;
	
	@Override
	public void setUp()
	{
		speciesDb = new SpeciesDatabase( this.getInstrumentation().getContext() );
		
		try
		{
			speciesDb.startSpeciesDatabase();
			
		} catch( SQLException e )
		{
			fail( "SQL Exception was thrown: " + e.getMessage() );			
		}
	}
	
	public void testAddEntry()
	{
		Site testSite = new Site( 1, "test site", "ABCDEFGH", "a test site" );
		Species testSpecie = new Species( 1, "test species", "test comment" );
		Record testRec = new Record( 1, "John Smith", "0246897531", "email@test.com",
				testSite, testSpecie, -1.00, 1.00, new Date(), 'D', "siteimg.jpg", "speciesimg.jpg" );
		
		speciesDb.addRecord( testRec );
		
		/*
		 * If the test was correct then the ArrayList
		 *  should contain the element we just added
		 */
		assertTrue( speciesDb.getRecordList().contains( testRec ) );
	}
	
	public void testRemoveEntry()
	{
		Site testSite = new Site( 2, "test site two", "IJKLMNOP", "another test site" );
		Species testSpecie = new Species( 2, "test species two", "another test comment" );
		Record testRec = new Record( 2, "Maggie Smith", "13579864310", "test@email.com",
				testSite, testSpecie, 1.00, -1.00, new Date(), 'A', "siteimg2.jpg", "speciesimg2.jpg" );
		
		speciesDb.addRecord( testRec );
		speciesDb.removeRecord( testRec );
		
		assertTrue( speciesDb.getRecordList().contains( testRec ) );
	}
	
	public void testEditEntry()
	{
		Site testSite = new Site( 3, "test site two", "IJKLMNOP", "another test site" );
		Species testSpecie = new Species( 3, "test species two", "another test comment" );
		Record testRec = new Record( 3, "Maggie Smith", "13579864310", "test@email.com",
				testSite, testSpecie, 1.00, -1.00, new Date(), 'A', "siteimg3.jpg", "speciesimg3.jpg" );

		Site newTestSite = new Site( 4, "test site two", "IJKLMNOP", "another test site" );
		Species newTestSpecie = new Species( 4, "test species two", "another test comment" );
		Record newTestRec = new Record( 4, "Maggie Smith", "13579864310", "newTest@email.com",
				newTestSite, newTestSpecie, 1.00, -1.00, new Date(), 'A', "siteimg4.jpg", "speciesimg4.jpg" );
		
		speciesDb.addRecord( testRec );
		speciesDb.updateRecord( testRec, newTestRec );
		
		assertTrue( speciesDb.getRecordList().contains( newTestRec ) );
		assertFalse( speciesDb.getRecordList().contains( testRec ) );
	}
	
	@Override
	public void tearDown() throws Exception
	{
		speciesDb.closeSpeciesDatabase();
	}
}
