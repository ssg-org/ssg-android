package org.sredisvojgrad.ulica.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.sredisvojgrad.ulica.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class SettingsActivity extends RoboActivity implements View.OnClickListener {

    @InjectView(R.id.btnLanguages) private Button btnLanguages;
    @InjectView(R.id.btnAbout)private Button btnAbout;
    @InjectView(R.id.btnLogOut) private Button btnLogOut;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getActionBar().setHomeButtonEnabled(true);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
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
        if ( v == btnLanguages ) {
            Intent intent = new Intent(this, ActivityLanguages.class);
            startActivity(intent);
        } else if ( v == btnAbout ) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        } else if ( v == btnLogOut ) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void init (){

        btnLanguages.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        btnAbout.setOnClickListener(this);



    }


}
