/*
* @(#) EditRecording.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/
package uk.ac.aber.dcs.CS22120.grouptwelve;

 /**
* EditRecording - Page for editing the record
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.0 (put status of version here)
* @see (ref to related classes)
*/ 
public class EditRecording
{
    private SpeciesDatabase database;
    private Record recToEdit, newRecord;

    public EditRecording( SpeciesDatabase db, Record rec )
    {
        this.database = db;

        this.recToEdit = rec;
        newRecord = new Record( rec );

    }

    /**
     * not sure how the gui works
     * but these methods are intended to update the current record
     */
    public void editLocation( Site s )
    {
        recToEdit.setSite(s);
    }

    public void editSpecies( Species s )
    {
        recToEdit.setSpecies(s);
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
