package com.example.plantsrecorder;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        database.insert( "sites", null, getSiteValues( rec.site ) );
        database.insert( "plants", null, getSpecieValues( rec.species ) );
        database.insert( "records", null, getRecordValues( rec ) );
    }

    public void removeRecord( Record rec )
    {
        long rec_id = rec.id;
        long site_id = rec.site.getID();
        long specie_id = rec.species.getID();

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

        recordValues.put( "_id", rec.id );
        recordValues.put( "recorder", rec.recorder );
        recordValues.put( "contact_number", rec.contactNum );
        recordValues.put("SiteID", rec.site.getID());
        recordValues.put("SpeciesID", rec.species.getID());

        return recordValues;
    }

    public ArrayList<Record> getRecord()
    {
        ArrayList<Record> recordList = new ArrayList<Record>();

        Cursor recordCursor = database.rawQuery( "select * from records", null );
        Cursor sitesCursor = database.rawQuery( "select * from sites", null );
        Cursor plantsCursor = database.rawQuery( "select * from plants", null );

        if( recordCursor.moveToNext() )
        {
            while( recordCursor.isAfterLast() == false )
            {
                Site recSite = null;
                Species recSpecies = null;

                if( sitesCursor.moveToNext() )
                    while( sitesCursor.isAfterLast() == false )
                        if( recordCursor.getInt( recordCursor.getColumnIndex( "_id" ) )
                                == sitesCursor.getInt( sitesCursor.getColumnIndex( "_id" ) ) )
                        {
                            recSite = new Site
                                    (
                                            sitesCursor.getInt( sitesCursor.getColumnIndex( "_id" ) ),
                                            sitesCursor.getString( sitesCursor.getColumnIndex( "name" ) ),
                                            sitesCursor.getDouble( sitesCursor.getColumnIndex( "latitude" ) ),
                                            sitesCursor.getDouble( sitesCursor.getColumnIndex( "longitude" ) )
                                    );
                        }

                if( plantsCursor.moveToNext() )
                    while( plantsCursor.isAfterLast() == false )
                        if( recordCursor.getInt( recordCursor.getColumnIndex( "_id" ) )
                                == plantsCursor.getInt( plantsCursor.getColumnIndex( "_id" ) ) )
                        {
                            recSpecies = new Species
                                    (
                                            plantsCursor.getInt( plantsCursor.getColumnIndex( "_id" ) ),
                                            plantsCursor.getString( plantsCursor.getColumnIndex( "name" ) )
                                    );
                        }

                recordList.add( new Record
                        (
                                recordCursor.getInt( recordCursor.getColumnIndex( "_id" ) ),
                                recordCursor.getString(recordCursor.getColumnIndex("recorder")),
                                recordCursor.getString( recordCursor.getColumnIndex( "contact_number" ) ),
                                recordCursor.getString( recordCursor.getColumnIndex( "email" ) ),
                                recSite,
                                recSpecies,
                                new SimpleDateFormat( recordCursor.getString( recordCursor.getColumnIndex( "time" ) ), Locale.UK ),
                                recordCursor.getString( recordCursor.getColumnIndex( "abundance" ) ).charAt( 0 ),
                                recordCursor.getBlob( recordCursor.getColumnIndex( "scene_photo" ) ),
                                recordCursor.getBlob( recordCursor.getColumnIndex( "specimen_photo" ) )
                        ) );
            }
        }

        return recordList;
    }

    public ArrayList<Record> getRecordList( String typeToFilter, String filter )
    {
        ArrayList<Record> recordList = new ArrayList<Record>();

        Cursor recordCursor = database.rawQuery( "select * from records where "+typeToFilter+" == "+filter, null );
        Cursor sitesCursor = database.rawQuery( "select * from sites where "+typeToFilter+" == "+filter, null );
        Cursor plantsCursor = database.rawQuery( "select * from plants where "+typeToFilter+" == "+filter, null );

        if( recordCursor.moveToNext() )
        {
            while( recordCursor.isAfterLast() == false )
            {
                Site recSite = null;
                Species recSpecies = null;

                if( sitesCursor.moveToNext() )
                    while( sitesCursor.isAfterLast() == false )
                        if( recordCursor.getInt( recordCursor.getColumnIndex( "_id" ) )
                                == sitesCursor.getInt( sitesCursor.getColumnIndex( "_id" ) ) )
                        {
                            recSite = new Site
                                    (
                                            sitesCursor.getInt( sitesCursor.getColumnIndex( "_id" ) ),
                                            sitesCursor.getString( sitesCursor.getColumnIndex( "name" ) ),
                                            sitesCursor.getDouble( sitesCursor.getColumnIndex( "latitude" ) ),
                                            sitesCursor.getDouble( sitesCursor.getColumnIndex( "longitude" ) )
                                    );
                        }

                if( plantsCursor.moveToNext() )
                    while( plantsCursor.isAfterLast() == false )
                        if( recordCursor.getInt( recordCursor.getColumnIndex( "_id" ) )
                                == plantsCursor.getInt( plantsCursor.getColumnIndex( "_id" ) ) )
                        {
                            recSpecies = new Species
                                    (
                                            plantsCursor.getInt( plantsCursor.getColumnIndex( "_id" ) ),
                                            plantsCursor.getString( plantsCursor.getColumnIndex( "name" ) )
                                    );
                        }

                recordList.add( new Record
                        (
                                recordCursor.getInt( recordCursor.getColumnIndex( "_id" ) ),
                                recordCursor.getString(recordCursor.getColumnIndex("recorder")),
                                recordCursor.getString( recordCursor.getColumnIndex( "contact_number" ) ),
                                recordCursor.getString( recordCursor.getColumnIndex( "email" ) ),
                                recSite,
                                recSpecies,
                                new SimpleDateFormat( recordCursor.getString( recordCursor.getColumnIndex( "time" ) ), Locale.UK ),
                                recordCursor.getString( recordCursor.getColumnIndex( "abundance" ) ).charAt( 0 ),
                                recordCursor.getBlob( recordCursor.getColumnIndex( "scene_photo" ) ),
                                recordCursor.getBlob( recordCursor.getColumnIndex( "specimen_photo" ) )
                        ) );
            }
        }

        return recordList;
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
            +"abundance character"
            +"scene_photo blob"
            +"specimen_photo blob";

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
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        Log.w(SpeciesDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + "which will destroy all old data");

        db.execSQL( "DROP TABLE IF EXISTS plants" );
        db.execSQL("DROP TABLE IF EXISTS sites");
        db.execSQL("DROP TABLE IF EXISTS records");
    }
}
