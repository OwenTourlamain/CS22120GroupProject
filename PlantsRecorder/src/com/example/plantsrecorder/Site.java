package com.example.plantsrecorder;

/**
 * Created by adam on 01/01/15.
 */
public class Site
{
    private int id;
    private String name;
    private double latitude, longitude;

    public Site( int id, String name, double lat, double lng )
    {
        this.name = name;
        this.latitude = lat;
        this.longitude = lng;
    }

    public int getID() { return id; }
    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
