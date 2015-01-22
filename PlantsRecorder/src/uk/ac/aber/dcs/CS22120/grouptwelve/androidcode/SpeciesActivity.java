package uk.ac.aber.dcs.CS22120.grouptwelve.androidcode;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;  
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
 
public class SpeciesActivity extends Activity {
 
    private Spinner spinner1;
    private Button btnSubmit;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);
 
        spinner1 = (Spinner) findViewById(R.id.species_spinner);
        List<String> list = new ArrayList<String>();
        // will be populated using BSBI list
        list.add("BSBI list item 1");
        list.add("BSBI list item 2");
        list.add("BSBI list item 3");
        list.add("BSBI list item 4");
        list.add("BSBI list item 5");
  
         
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                     (this, android.R.layout.simple_spinner_item,list);
                      
        dataAdapter.setDropDownViewResource
                     (android.R.layout.simple_spinner_dropdown_item);
                      
        spinner1.setAdapter(dataAdapter);
         
        // Spinner item selection Listener  
        addListenerOnSpinnerItemSelection();
         
        // Button click Listener 
        addListenerOnButton();
         
 
    }
 
    // Add spinner data
     
    public void addListenerOnSpinnerItemSelection(){
         
                spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
     
    //get the selected dropdown list value
     
    public void addListenerOnButton() {
 
        spinner1 = (Spinner) findViewById(R.id.species_spinner);
         
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
 
        btnSubmit.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
 
                Toast.makeText(SpeciesActivity.this,
                        "On Button Click : " + 
                        "\n" + String.valueOf(spinner1.getSelectedItem()) ,
                        Toast.LENGTH_LONG).show();
            }
 
        });
 
    }
    
    public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radio_manual:
	            if (checked){
	            	Intent myIntent = new Intent(view.getContext(), SpeciesCreateActivity.class);
		            startActivityForResult(myIntent, 0);
	            }
	                
	            break;
	    }
    
   
    }
}
    
    
 
