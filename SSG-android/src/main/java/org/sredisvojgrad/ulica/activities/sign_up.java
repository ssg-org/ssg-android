package org.sredisvojgrad.ulica.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.sredisvojgrad.ulica.R;

import roboguice.activity.RoboActivity;

public class sign_up extends RoboActivity implements View.OnClickListener {

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

}
