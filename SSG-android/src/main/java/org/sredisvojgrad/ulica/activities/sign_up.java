package org.sredisvojgrad.ulica.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.sredisvojgrad.ulica.MainActivity;
import org.sredisvojgrad.ulica.R;
import org.sredisvojgrad.ulica.api.GetCitiesAndCategories;
import org.sredisvojgrad.ulica.api.SsgCommunicatorInterface;
import org.sredisvojgrad.ulica.model.SyncData;

import java.util.Objects;

public class sign_up extends ActionBarActivity  implements SsgCommunicatorInterface {



    Button btnGradovi;
    private GetCitiesAndCategories en;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        addListenerOnButton();
        addListenerOnButton2();

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
    public void addListenerOnButton() {


        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(sign_up.this, MainActivity.class);
                startActivity(intent);

            }

        });

    }
    public void addListenerOnButton2() {


        button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(sign_up.this, MainActivity.class);
                startActivity(intent);

            }

        });

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
