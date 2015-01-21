package com.example.plantsrecorder;

import java.text.SimpleDateFormat;

/**
 * Simple record structure
 */
public class Record
{
    public int id;
    public String recorder, contactNum, email;
    public Site site;
    public Species species;
    public SimpleDateFormat time;
    public char abundance;
    public byte[] scenePhoto, specimenPhoto;

    public Record( int id, String recorder, String contactNum, String email, Site site, Species species,
                   SimpleDateFormat time, char abundance, byte[] scenePhoto, byte[] specimenPhoto )
    {
        this.id = id;
        this.recorder = recorder;
        this.contactNum = contactNum;
        this.email = email;
        this.site = site;
        this.species = species;
        this.time = time;
        this.abundance = abundance;
        this.scenePhoto = scenePhoto;
        this.specimenPhoto = specimenPhoto;
    }

    public Record( Record rec )
    {
        this.id = rec.id;
        this.recorder = rec.recorder;
        this.contactNum = rec.contactNum;
        this.email = rec.email;
        this.site = rec.site;
        this.species = rec.species;
        this.time = rec.time;
        this.abundance = rec.abundance;
        this.scenePhoto = rec.scenePhoto;
        this.specimenPhoto = rec.specimenPhoto;
    }
}
