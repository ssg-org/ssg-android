package org.sredisvojgrad.ulica.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.sredisvojgrad.ulica.R;
import org.sredisvojgrad.ulica.api.GetCitiesAndCategories;
import org.sredisvojgrad.ulica.api.ReportIssueService;
import org.sredisvojgrad.ulica.api.SsgCommunicatorInterface;
import org.sredisvojgrad.ulica.entity.issue;
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


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_problem);
        getActionBar().setHomeButtonEnabled(true);
        btnCategory = (Button) findViewById(R.id.btnCategory);
        btnCitySet = (Button) findViewById(R.id.btnCitySet);
        eTDescription = (EditText) findViewById(R.id.eTDescription);
        eTTitle = (EditText) findViewById(R.id.eTTitle);
        passedVar = getIntent().getStringExtra(CategoriesActivity.ID_EXTRA);
        en = new GetCitiesAndCategories(this, this);
        en.getCitiesAndCategories();
        en1 = new GetCitiesAndCategories(this, this);
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
        getMenuInflater().inflate(R.menu.nextmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_next:
                nextMenuItem();
                break;
        }
        return true;
    }

    private void nextMenuItem() {
        if ((eTTitle.getText().toString()).isEmpty() || (eTDescription.getText().toString()).isEmpty() || (btnCategory.getText().toString()).isEmpty() || (btnCitySet.getText().toString()).isEmpty())
            Toast.makeText(this, "Please fill all information.", Toast.LENGTH_SHORT).show();
        else {
            issue newIssue = new issue();
            String title = eTTitle.getText().toString();
            String description = eTDescription.getText().toString();
            String city = btnCitySet.getText().toString();
            String category = btnCategory.getText().toString();
            newIssue.setCity_id(city);
            newIssue.setCategory_id(category);
            newIssue.setTitle(title);
            newIssue.setDescription(description);
            ReportIssueService issueService = new ReportIssueService(this);
            issueService.reportIssue(newIssue);
            Intent intent = new Intent(SetProblemActivity.this, MapActivity.class);
            startActivity(intent);
        }
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

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
