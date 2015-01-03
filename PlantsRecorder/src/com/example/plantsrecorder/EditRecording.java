package com.example.plantsrecorder;

import android.app.Activity;

/**
 * Page for editing the record
 *
 * Probably might be GUI as well
 *
 */
public class EditRecording extends Activity
{
    private SpeciesDatabase database;
    private Record recToEdit, newRecord;

    public EditRecording( SpeciesDatabase db, Record rec )
    {
        this.database = db;

        this.recToEdit = rec;
        newRecord = new Record( rec );

        // set GUI elements to record data here
    }

    /**
     * not sure how the gui works
     * but these methods are intended to update the current record
     */
    public void editLocation( Site s )
    {
        recToEdit.site = s;
    }

    public void editSpecies( Species s )
    {
        recToEdit.species = s;
    }

    /**
     * this method is supposed to do the final 'edit' and
     * make the change in the database
     */
    public void commitEdit()
    {
        database.updateRecord( recToEdit, newRecord );
    }
}
