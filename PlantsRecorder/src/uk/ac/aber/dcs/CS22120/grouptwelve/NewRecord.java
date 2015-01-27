/*
* @(#) NewRecord.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/

package uk.ac.aber.dcs.CS22120.grouptwelve;

 /**
* NewRecord - This class is responsible for adding data to the database.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class NewRecord
{
    private SpeciesDatabase database;

    public NewRecord( SpeciesDatabase db )
    {
        this.database = db;
    }

    /**
     * validate the given record
     * 
     * @param rec
     * @return true if valid, false if not
     */
    public boolean validateRecord( Record rec )
    {
        // do some validations, for example you might want to check
        // if the record already exists within the database

        return true;
    }

    /**
     * add the supplied record to the database
     * 
     * @param rec
     */
    public void addToDatabase( Record rec )
    {
        database.addRecord( rec );
    }
}
