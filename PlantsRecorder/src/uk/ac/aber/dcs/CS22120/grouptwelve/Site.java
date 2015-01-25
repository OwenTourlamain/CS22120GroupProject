package uk.ac.aber.dcs.CS22120.grouptwelve;

/**
 * simple container object for a site record
 */
public class Site
{
    private int id;
    private String name;
    private String gridRef;
    private String description;

    public Site( int id, String name, String gridRef, String description )
    {
    	this.id = id;
        this.name = name;
        this.gridRef = gridRef;
        this.description = description;
    }

    public int getID() { return id; }
    public String getName() { return name; }
    public String getGridReference() { return gridRef; }
    public String getDescription() { return description; }
}
