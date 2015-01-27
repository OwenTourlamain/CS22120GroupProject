/*
* @(#) Record.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 

package uk.ac.aber.dcs.CS22120.grouptwelve;

import java.io.Serializable;
import java.util.Date;

 /**
* Record - This class contains the structure of a record.
* <p>
* How it is used
*
* @author (name)
* @since 1.0
* @version 1.X (put status of version here)
* @see (ref to related classes)
*/ 
public class Record implements Serializable
{
   
	private static final long serialVersionUID = 7565158491163087607L;
	
	private int id;
   
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public char getAbundance() {
		return abundance;
	}

	public void setAbundance(char abundance) {
		this.abundance = abundance;
	}

	public String getScenePhoto() {
		return scenePhoto;
	}

	public void setScenePhoto(String scenePhoto) {
		this.scenePhoto = scenePhoto;
	}

	public String getSpecimenPhoto() {
		return specimenPhoto;
	}

	public void setSpecimenPhoto(String specimenPhoto) {
		this.specimenPhoto = specimenPhoto;
	}

	public double getLongitude() { return this.longitude; }
	public double getLatitude() { return this.latitude; }
	
	public void setLongitude( double longitude ) { this.longitude = longitude; }
	public void setLatitude( double latitude ) { this.latitude = latitude; }
	
	public void setPhoneNumber( String phoneNumber ) { this.phoneNumber = phoneNumber; }
	public String getPhoneNumber() { return this.phoneNumber; }
	
	private String recorder, contactNum, email;
    private Site site;
    private Species species;
    private Date time;
    private String phoneNumber;
    private double longitude, latitude;
    private char abundance;
    private String scenePhoto, specimenPhoto; // Stored as the filename

    /**
     * physical constructor
     * 
     */
    public Record( int id, String recorder, String contactNum, String email, Site site, Species species,
                   double longitude, double latitude, Date time, char abundance, String scenePhoto, String specimenPhoto )
    {
        this.id = id;
        this.recorder = recorder;
        this.contactNum = contactNum;
        this.email = email;
        this.site = site;
        this.species = species;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
        this.abundance = abundance;
        this.scenePhoto = scenePhoto;
        this.specimenPhoto = specimenPhoto;
    }

    /**
     * copy constructor
     * 
     */
    public Record( Record rec )
    {
        this.id = rec.id;
        this.recorder = rec.recorder;
        this.contactNum = rec.contactNum;
        this.email = rec.email;
        this.site = rec.site;
        this.species = rec.species;
        this.time = rec.time;
        this.longitude = rec.longitude;
        this.latitude = rec.latitude;
        this.abundance = rec.abundance;
        this.scenePhoto = rec.scenePhoto;
        this.specimenPhoto = rec.specimenPhoto;
    }
    
    public Record()
    {
    	// blank record, to indicate a new record
    }
}
