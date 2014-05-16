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

public class SetProblemActivity extends ActionBarActivity implements SsgCommunicatorInterface, View.OnClickListener {

    private Button btnCategory;
    private Button btnCitySet;
    private EditText eTDescription;
    private EditText eTTitle;
    String passedVar = null;
    String passedCity = null;
    private GetCitiesAndCategories en;
    private GetCitiesAndCategories en1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_problem);
        btnCategory = (Button) findViewById(R.id.btnCategory);
        btnCitySet = (Button) findViewById(R.id.btnCitySet);
        eTDescription = (EditText) findViewById(R.id.eTDescription);
        eTTitle = (EditText) findViewById(R.id.eTTitle);
        passedVar = getIntent().getStringExtra(CategoriesActivity.ID_EXTRA);
        en = new GetCitiesAndCategories(this, this);
        en.getCitiesAndCategories();
        en1 = new GetCitiesAndCategories(this,this);
        en1.getCitiesAndCategories();
        passedCity = getIntent().getStringExtra(SetProblemCitiesActivity.ID_EXTRA);

        //na oba pise isto?
        btnCategory.setText(passedVar);
        btnCitySet.setText(passedCity);
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
        if (v == btnCategory) {
            Intent intent = new Intent(SetProblemActivity.this, CategoriesActivity.class);
            startActivity(intent);
        } else if (v == btnCitySet) {
            Intent intent = new Intent(SetProblemActivity.this, SetProblemCitiesActivity.class);
            startActivity(intent);
        }
    }

    private void init() {

        btnCategory.setOnClickListener(this);
        btnCitySet.setOnClickListener(this);


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
