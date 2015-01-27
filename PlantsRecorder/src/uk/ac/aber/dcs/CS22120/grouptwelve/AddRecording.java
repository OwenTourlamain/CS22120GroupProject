/*
* @(#) AddRecording.java 1.1 2015-01-27
*
* Copyright (c) 2015 Aberystwyth University.
* All rights reserved.
*
*/ 

package uk.ac.aber.dcs.CS22120.grouptwelve;

import java.util.ArrayList;
import android.widget.EditText;

 /**
* SomeClass - A class that does something.
* <p>
* How it is used
*
* @author Adam 31/12/14
* @since 1.0
* @version 1.0 
* @see (ref to related classes)
*/
public class AddRecording
{
    ArrayList<Species> plantList = new ArrayList<Species>();
	private EditText edittext;
	private EditText name;
	private EditText id;
	private EditText comment;
	
	
    public void addRecording() {}
    
    public void setGPSLocation() {}
    
    public void addSpecies() {
        
//        name = (EditText) findViewById(R.id.nameEntryField);
//		id = (EditText) findViewById(R.id.idEntryField);
//		comment = (EditText) findViewById(R.id.commentEntryField);
//
//		Species tempSpecies = new Species();
//		tempSpecies.setID(id);
//		tempSpecies.setName(name);
//		tempSpecies.setComment(comment);
//		plantList.add(tempSpecies);
    }
    
    public void addDescription() {
//        	edittext = (EditText) findViewById(R.id.commentsEntryField);
    }
    
    public void addPhoto() {}
    
    public void save() {}

    
}
