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
import roboguice.inject.InjectView;

public class SetProblemActivity extends RoboActivity implements View.OnClickListener {

    @InjectView(R.id.btnCategory)   private Button btnCategory;
    @InjectView(R.id.btnCitySet)    private Button btnCitySet;
    @InjectView(R.id.eTDescription) private EditText eTDescription;
    @InjectView(R.id.eTTitle)       private EditText eTTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_problem);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.set_problem, menu);
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
    public void onClick(View v) {
        if ( v == btnCategory ) {
            Intent intent = new Intent(SetProblemActivity.this, CategoriesActivity.class);
            startActivity(intent);
        } else if ( v == btnCitySet ) {
            Intent intent = new Intent(SetProblemActivity.this, SetProblemCitiesActivity.class);
            startActivity(intent);
        }
    }
    private void init (){

        btnCategory.setOnClickListener(this);
        btnCitySet.setOnClickListener(this);



    }
}
