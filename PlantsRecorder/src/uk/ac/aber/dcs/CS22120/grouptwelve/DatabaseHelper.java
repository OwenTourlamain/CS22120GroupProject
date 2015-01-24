package uk.ac.aber.dcs.CS22120.grouptwelve;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Helper class that constructs the database
 *
 */
class DatabaseHelper extends SQLiteOpenHelper 
{
    private static final String DATABASE_NAME = "records.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_RECORDS_TABLE =
            "CREATE TABLE RECORDS ("
            +"RecordID       BIGINT AUTOMATIC PRIMARY KEY"
            + "Recorder      VARCHAR(255)"
            + "ContactNumber VARCHAR(255)"
            + "Email         VARCHAR(255)"
            + "SiteID        BIGINT UNSIGNED"
            + "SpeciesID     BIGINT UNSIGNED"
            + "Time          TIMESTAMP"
            + "Longitude     DECIMAL(15,10)"
            + "Latitude      DECIMAL(15,10)"
            + "Abundance     CHAR(1)"
            + "SceneImg      VARCHAR(1024)"
            + "SpecimenImg   VARCHAR(1024)"
            + ")";

    private static final String CREATE_SITES_TABLE =
            "CREATE TABLE SITES ("
            + "SiteID      BIGINT UNSIGNED AUTOMATIC PRIMARY KEY"
            + "Name        VARCHAR(255)"
            + "Location    VARCHAR(255)"
            + "Description TEXT"
            + ")";

    private static final String CREATE_SPECIES_TABLE =
    		"CREATE TABLET SPECIES ("
    		+ "SpeciesID BIGINT UNSIGNED PRIMARY KEY AUTOMATIC"
    		+ "Name      VARCHAR(255)"
    		+ ")";
    		
    private static final String DATABASE_CREATE =
             CREATE_SPECIES_TABLE
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