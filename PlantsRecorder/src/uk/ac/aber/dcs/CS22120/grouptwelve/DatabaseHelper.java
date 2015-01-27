/*
* @(#) DatabaseHelper.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 

package uk.ac.aber.dcs.CS22120.grouptwelve;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

 /**
* DatabaseHelper - Helper class that constructs the database
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 

class DatabaseHelper extends SQLiteOpenHelper 
{
    private static final String DATABASE_NAME = "plants.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_RECORDS_TABLE =
            "CREATE TABLE RECORDS ("
            + "RecordID      BIGINT PRIMARY KEY,"
            + "Recorder      VARCHAR(255),"
            + "ContactNumber VARCHAR(255),"
            + "Email         VARCHAR(255),"
            + "SiteID        BIGINT UNSIGNED,"
            + "SpeciesID     BIGINT UNSIGNED,"
            + "Time          TIMESTAMP,"
            + "Longitude     DECIMAL(15,10),"
            + "Latitude      DECIMAL(15,10),"
            + "Abundance     CHAR(1),"
            + "SceneImg      VARCHAR(1024),"
            + "SpecimenImg   VARCHAR(1024)"
            + ")";

    private static final String CREATE_SITES_TABLE =
            "CREATE TABLE SITES ("
            + "SiteID      BIGINT UNSIGNED PRIMARY KEY,"
            + "Name        VARCHAR(255),"
            + "Location    VARCHAR(8),"
            + "Description TEXT"
            + ")";

    private static final String CREATE_SPECIES_TABLE =
    		"CREATE TABLE SPECIES ("
    		+ "SpeciesID BIGINT UNSIGNED PRIMARY KEY,"
    		+ "Name      VARCHAR(255)"
    		+ ")";

    public DatabaseHelper( Context context )
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate( SQLiteDatabase db )
    {
        db.execSQL(CREATE_SPECIES_TABLE);
        db.execSQL(CREATE_SITES_TABLE);
        db.execSQL(CREATE_RECORDS_TABLE);
        
        Log.v( "SpeciesDatabase", "Tables created" );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
    {
        Log.w(SpeciesDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + "which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS RECORDS");
        db.execSQL("DROP TABLE IF EXISTS SITES");
        db.execSQL("DROP TABLE IF EXISTS SPECIES");
    }
}
