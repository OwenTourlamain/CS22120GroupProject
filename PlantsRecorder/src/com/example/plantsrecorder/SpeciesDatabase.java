package com.example.plantsrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by adam on 31/12/14.
 */
public class SpeciesDatabase
{
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public SpeciesDatabase( Context context )
    {
        dbHelper = new DatabaseHelper( context );
    }

    public void startDatabase() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void closeDatabase()
    {
        dbHelper.close();
    }

    public void addRecord( Record rec )
    {
        database.insert( "sites", null, getSiteValues( rec.getSite() ) );
        database.insert( "plants", null, getSpecieValues( rec.getSpecies() ) );
        database.insert( "records", null, getRecordValues( rec ) );
    }

    public void removeRecord( Record rec )
    {
        long rec_id = rec.getID();
        long site_id = rec.getSite().getID();
        long specie_id = rec.getSpecies().getID();

        database.delete( "records", "_id = " + rec_id, null );
        database.delete( "sites", "_id = " + site_id, null );
        database.delete( "plants", "_id = " + specie_id, null );
    }

    public void updateRecord( Record rOld, Record rNew )
    {
        removeRecord( rOld );
        addRecord( rNew );
    }

    private ContentValues getSpecieValues( Species s )
    {
        ContentValues specieValues = new ContentValues();

        specieValues.put( "_id", s.getID() );
        specieValues.put( "species", s.getName() );

        return specieValues;
    }

    private ContentValues getSiteValues( Site s )
    {
        ContentValues siteValues = new ContentValues();

        siteValues.put( "_id", s.getID() );
        siteValues.put( "name", s.getName() );
        siteValues.put( "latitude", s.getLatitude() );
        siteValues.put( "longitude", s.getLongitude() );

        return siteValues;
    }

    private ContentValues getRecordValues( Record rec )
    {
        ContentValues recordValues = new ContentValues();

        recordValues.put( "_id", rec.getID() );
        recordValues.put( "recorder", rec.getRecorder() );
        recordValues.put( "contact_number", rec.getContactNum() );
        recordValues.put( "SiteID", rec.getSite().getID() );
        recordValues.put( "SpeciesID", rec.getSpecies().getID() );

        return recordValues;
    }
}

class DatabaseHelper extends SQLiteOpenHelper // Private helper class
{
    private static final String DATABASE_NAME = "records.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_PLANTS_TABLE =
            "create table plants ("
            +"_id integer primary key"
            +"species text not null )";

    private static final String CREATE_RECORDS_TABLE =
            "create table records ("
            +"_id integer primary key"
            +"recorder text not null"
            +"contact_number text"
            +"SiteID integer foreign key"
            +"SpeciesID integer foreign key"
            +"time datetime"
            +"abundance character";

    private static final String CREATE_SITES_TABLE =
            "create table sites ("
            +"_id integer primary key"
            +"name text not null"
            +"latitude double precision"
            +"longitude double precision";

    private static final String DATABASE_CREATE =
             CREATE_PLANTS_TABLE
            +CREATE_SITES_TABLE
            +CREATE_RECORDS_TABLE;

    public DatabaseHelper( Context context )
    {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL( DATABASE_CREATE );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        Log.w(SpeciesDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + "which will destroy all old data");

        db.execSQL( "DROP TABLE IF EXISTS plants" );
        db.execSQL( "DROP TABLE IF EXISTS sites" );
        db.execSQL( "DROP TABLE IF EXISTS records" );
    }
}
