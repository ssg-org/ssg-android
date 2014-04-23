package org.sredisvojgrad.ulica.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.sredisvojgrad.ulica.R;
import org.sredisvojgrad.ulica.api.GetCitiesAndCategories;
import org.sredisvojgrad.ulica.api.SsgCommunicatorInterface;
import org.sredisvojgrad.ulica.model.SyncData;

import java.util.Objects;
import roboguice.activity.RoboActivity;

public class sign_up extends ActionBarActivity  implements SsgCommunicatorInterface,View.OnClickListener  {




    Button btnGradovi;
    private GetCitiesAndCategories en;



    private Button btnBack;
    private EditText eTName;
    private EditText eTSurname;
    private EditText eTEmail;
    private EditText eTPassword;
    private Button btnSignUp;
    private Button btnCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnGradovi =(Button)findViewById(R.id.btnGradovi);
        btnGradovi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent i = new Intent(sign_up.this,CitiesActivity.class);
               startActivity(i);

            }
        });



    en= new GetCitiesAndCategories(this,this);

        en.getCitiesAndCategories();

        System.out.println("OnCreate");


    }




    @Override
    public void onClick(View v) {
        if ( v == btnBack ) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if ( v == btnSignUp ) {
            // Handle clicks for btnSignUp
        } else if ( v == btnCity ) {
            Intent intent = new Intent(this, ActivityCity.class);
            startActivity(intent);
        }
    }

    private void init (){

        btnBack.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnCity.setOnClickListener(this);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void recivedData(SyncData data) {

    }

    @Override
    public void fetchingData(Error error) {

    }

    @Override
    public void getResponse(String code, Objects responseObject) {

    }
}
