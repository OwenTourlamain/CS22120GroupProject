package uk.ac.aber.dcs.CS22120.grouptwelve;


/**
 * This is the part of the app responsible for adding stuff to the database
 *
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
