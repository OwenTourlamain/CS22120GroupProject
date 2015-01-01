package com.example.plantsrecorder;

import android.text.format.Time;

import java.io.File;

/**
 * Created by adam on 01/01/15.
 */
public class Record
{
    private int id;
    private String recorder;
    private String contactNum;
    private String email;
    private Site site;
    private Species species;
    private Time time;
    private char abundance;
    private File scenePhoto;
    private File specimenPhoto;

    public Record( int id, String recorder, String contactNum, String email, Site site, Species species,
                   Time time, char abundance, File scenePhoto, File specimenPhoto )
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

    public int getID() { return id; }
    public String getRecorder() { return recorder; }
    public String getContactNum() { return contactNum; }
    public String getEmail() { return email; }
    public Site getSite() { return site; }
    public Species getSpecies() { return species; }
    public Time getTime() { return time; }
    public char getAbundance() { return abundance; }
    public File getScenePhoto() { return scenePhoto; }
    public File getSpecimenPhoto() { return specimenPhoto; }
}
