package uk.ac.aber.dcs.CS22120.grouptwelve;

/**
 * Created by adam on 01/01/15.
 */
public class Species
{
    private int id;
    private String name;

    public Species( int id, String name )
    {
        this.id = id;
        this.name = name;
    }

    public int getID() { return id; }
    public String getName() { return name; }
}