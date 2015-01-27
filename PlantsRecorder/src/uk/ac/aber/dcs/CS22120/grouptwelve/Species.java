package uk.ac.aber.dcs.CS22120.grouptwelve;

import java.io.Serializable;

/**
 * Simple object class to represent a species
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
