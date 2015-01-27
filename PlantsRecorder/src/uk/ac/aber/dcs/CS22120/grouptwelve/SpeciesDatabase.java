/*
* @(#) SpeciesDatabase.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 
package uk.ac.aber.dcs.CS22120.grouptwelve;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
* SpeciesDatabase - This is a front-end for the local SQL database.
* <p>
* Entries can be added/removed/edited from here,
* then when ready the database will be synchronised with the site.
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class SpeciesDatabase
{
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper; // an sql wrapper

    /**
     * @param context current android context, used to initialise the DB helper
     */
    public SpeciesDatabase( Context context )
    {
        dbHelper = new DatabaseHelper( context );
    }

    /**
     * Initialises the database
     * @throws SQLException
     */
    public void startSpeciesDatabase() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Stop the SQL database
     */
    public void closeSpeciesDatabase()
    {
        database.close();
        dbHelper.close();
    }

    /**
     * Adds the record to the database
     * 
     * @param rec the record to be added
     */
    public void addRecord( Record rec )
    {
    	Log.v( "SpeciesDatabase", "Logging site id:" + rec.getSite().getID() );
        database.insert( "SITES", null, getSiteValues( rec.getSite() ) );
        Log.v("SpeciesDatabase", "Logging species id:" + rec.getSpecies().getID() );
        database.insert( "SPECIES", null, getSpecieValues( rec.getSpecies() ) );
        Log.v( "SpeciesDatabase", "Logging record id:" + rec.getId() );
        database.insert( "RECORDS", null, getRecordValues( rec ) );
    }

    /**
     * removes the given record from the database
     * 
     * @param rec
     */
    public void removeRecord( Record rec )
    {
    	// some kind of mark for deletion field
    }

    /**
     * Updates the given record with a newer record
     * 
     * @param rOld
     * @param rNew
     */
    public void updateRecord( Record rOld, Record rNew )
    {
        removeRecord( rOld );
        addRecord( rNew );
    }

    /**
     * Converts the species into a format more usable by
     * the database
     * 
     * @param s
     * @return
     */
    private ContentValues getSpecieValues( Species s )
    {
        ContentValues specieValues = new ContentValues();

        specieValues.put( "SpeciesID", s.getID() );
        specieValues.put( "Name", s.getName() );

        return specieValues;
    }

    /**
     * converts the given site into the database format
     * 
     * @param s
     * @return
     */
    private ContentValues getSiteValues( Site s )
    {
        ContentValues siteValues = new ContentValues();

        siteValues.put( "SiteID", s.getID() );
        siteValues.put( "Name", s.getName() );
        siteValues.put( "Location", s.getGridReference() );
        siteValues.put( "Description", s.getDescription() );

        return siteValues;
    }

    /**
     * converts the given record into the database format
     * 
     * @param rec
     * @return
     */
    private ContentValues getRecordValues( Record rec )
    {
        ContentValues recordValues = new ContentValues();

        recordValues.put( "RecordID", rec.getId() );
        recordValues.put( "Recorder", rec.getRecorder() );
        recordValues.put( "ContactNumber", rec.getContactNum() );
        recordValues.put( "Email", rec.getEmail() );
        recordValues.put("SiteID", rec.getSite().getID());
        recordValues.put("SpeciesID", rec.getSpecies().getID());
        recordValues.put( "Time", rec.getTime().toString() );
        recordValues.put( "Longitude", rec.getLongitude() );
        recordValues.put( "Latitude", rec.getLatitude() );
        recordValues.put( "Abundance", "" + rec.getAbundance() );
        recordValues.put( "SceneImg", rec.getScenePhoto() );
        recordValues.put( "SpecimenImg", rec.getSpecimenPhoto() );

        return recordValues;
    }

    /**
     * Used to acquire an unfiltered list of all RECORDS
     * currently in the database
     * 
     * @return the unfiltered list
     */
    public ArrayList<Record> getRecordList()
    {
        ArrayList<Record> recordList = new ArrayList<Record>();
        Site recSite = null;
        Species recSpecies = null;
        
        Cursor recordCursor = database.rawQuery( "SELECT * FROM RECORDS", null );
        Cursor sitesCursor = database.rawQuery( "SELECT * FROM SITES", null );
        Cursor speciesCursor = database.rawQuery( "SELECT * FROM SPECIES", null );

        if( recordCursor.moveToNext() )
        {
            while( recordCursor.isAfterLast() == false )
            {
                recSite = makeSite( recordCursor, sitesCursor );
                recSpecies = makeSpecies( recordCursor, sitesCursor );

                recordList.add( makeNewRecord( recordCursor, sitesCursor, speciesCursor, recSite, recSpecies ) );
            }
        }

        return recordList;
    }

    /**
     * returns a list that contains only entries matching the given filter criteria
     * 
     * @param typeToFilter
     * @param filter
     * @return
     */
    public ArrayList<Record> getRecordList( String typeToFilter, String filter )
    {
        ArrayList<Record> recordList = new ArrayList<Record>();

        Cursor recordCursor = database.rawQuery( "select * from RECORDS where "+typeToFilter+" == "+filter, null );
        Cursor sitesCursor = database.rawQuery( "select * from SITES where "+typeToFilter+" == "+filter, null );
        Cursor speciesCursor = database.rawQuery( "select * from SPECIES where "+typeToFilter+" == "+filter, null );

        if( recordCursor.moveToNext() )
        {
            while( recordCursor.isAfterLast() == false )
            {
            	Site recSite = makeSite( recordCursor, sitesCursor );
            	Species recSpecies = makeSpecies( recordCursor, speciesCursor );
                recordList.add( makeNewRecord( recordCursor, sitesCursor, speciesCursor, recSite, recSpecies ) );
            }
        }

        return recordList;
    }
    
    /**
     * refactored method to make constructing a record easier
     * 
     * @param recordCursor
     * @param sitesCursor
     * @param speciesCursor
     * @return
     */
    private Record makeNewRecord( Cursor recordCursor, Cursor sitesCursor, Cursor speciesCursor, Site recSite, Species recSpecies )
    {
        return new Record
        (
            recordCursor.getInt( recordCursor.getColumnIndex( "RecordID" ) ),
            recordCursor.getString(recordCursor.getColumnIndex("Recorder")),
            recordCursor.getString( recordCursor.getColumnIndex( "ContactNumber" ) ),
            recordCursor.getString( recordCursor.getColumnIndex( "Email" ) ),
            recSite,
            recSpecies,
            recordCursor.getDouble( recordCursor.getColumnIndex( "Latitude" ) ),
            recordCursor.getDouble( recordCursor.getColumnIndex( "Longitude" ) ),
            new Date( recordCursor.getLong( recordCursor.getColumnIndex( ""))),
            recordCursor.getString( recordCursor.getColumnIndex( "Abundance" ) ).charAt( 0 ),
            recordCursor.getString( recordCursor.getColumnIndex( "ScenePhoto" ) ),
            recordCursor.getString( recordCursor.getColumnIndex( "SpecimenPhoto" ) )
        );
    }

    /**
     * Refactored method to make constructing a species easier
     * 
     * @param recordCursor
     * @param speciesCursor
     * @param recSpecies
     * @return
     */
	private Species makeSpecies(Cursor recordCursor, Cursor speciesCursor )
	{
		Species recSpecies = null;
		
		if( speciesCursor.moveToNext() )
		    while( speciesCursor.isAfterLast() == false )
		        if( recordCursor.getInt( recordCursor.getColumnIndex( "RecordID" ) )
		                == speciesCursor.getInt( speciesCursor.getColumnIndex( "SpeciesID" ) ) )
		        {
		            recSpecies = new Species
		                    (
		                            speciesCursor.getInt( speciesCursor.getColumnIndex( "SpeciesID" ) ),
		                            speciesCursor.getString( speciesCursor.getColumnIndex( "Name" ) ),
		                            speciesCursor.getString( speciesCursor.getColumnIndex( "Comment" ) )
		                    );
		        }
		
		return recSpecies;
	}

	/**
	 * Refactored method to make constructing a species easier
	 * 
	 * @param recordCursor
	 * @param sitesCursor
	 * @param recSite
	 * @return
	 */
	private Site makeSite(Cursor recordCursor, Cursor sitesCursor )
	{
		Site recSite = null;
		
		if( sitesCursor.moveToNext() )
		    while( sitesCursor.isAfterLast() == false )
		        if( recordCursor.getInt( recordCursor.getColumnIndex( "RecordID" ) )
		                == sitesCursor.getInt( sitesCursor.getColumnIndex( "SiteID" ) ) )
		        {
		            recSite = new Site
		                    (
		                            sitesCursor.getInt( sitesCursor.getColumnIndex( "SiteID" ) ),
		                            sitesCursor.getString( sitesCursor.getColumnIndex( "Name" ) ),
		                            sitesCursor.getString( sitesCursor.getColumnIndex( "Location" ) ),
		                            sitesCursor.getString( sitesCursor.getColumnIndex( "Description" ) )
		                    );
		        }
		
		return recSite;
	}
}
