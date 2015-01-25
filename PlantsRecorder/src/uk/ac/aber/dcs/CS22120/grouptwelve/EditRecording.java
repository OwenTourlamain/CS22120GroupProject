package uk.ac.aber.dcs.CS22120.grouptwelve;


/**
 * Page for editing the record
 *
 * Probably might be GUI as well
 *
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
