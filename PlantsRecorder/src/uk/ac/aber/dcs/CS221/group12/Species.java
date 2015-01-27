/*
* @(#) Species.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 

package uk.ac.aber.dcs.CS221.group12;

import java.io.Serializable;

/**
 * Simple object class to represent a species
 */
 
/**
* Species - Simple object class to represent a species
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class Species implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1684544462612416203L;
	
	private int id;
	private String name;
	private String comment;

	public Species( int id, String name, String comment )
	{
		this.id = id;
		this.name = name;
		this.comment = comment;
	}

	public void setID(int i) {
		id = i;
	}

	public void setName(String n) {
		name = n;
	}

	public void setComment(String c) {
		comment = c;

	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}
}
