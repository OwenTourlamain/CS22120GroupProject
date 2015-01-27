/*
* @(#) Site.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 

package uk.ac.aber.dcs.CS22120.grouptwelve;

import java.io.Serializable;

/**
* Site - A class that contains a simple container object for a record.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 

public class Site implements Serializable
{
    
	private static final long serialVersionUID = 7399586458373747320L;
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

    public Site( String name, String description )
    {
    	this.name = name;
    	this.description = description;
    }
    
    public int getID() { return id; }
    public String getName() { return name; }
    public String getGridReference() { return gridRef; }
    public String getDescription() { return description; }
}
