package com.example.plantsrecorder;

import android.app.Activity;

/**
 * This is the part of the app responsible for adding stuff to the database
 *
 * This should also feature the GUI(?)
 */
public class NewRecord extends Activity
{
    private SpeciesDatabase database;

    public NewRecord( SpeciesDatabase db )
    {
        this.database = db;
    }

    public boolean validateRecord( Record rec )
    {
        // do some validations, for example you might want to check
        // if the record already exists within the database

        return true;
    }

    public void addToDatabase( Record rec )
    {
        database.addRecord( rec );
    }
}
