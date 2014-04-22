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

public class PhotoActivity extends RoboActivity implements View.OnClickListener{

    private Button btnSettings;
    private Button btnCamera;
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        getActionBar().setHomeButtonEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.photo, menu);
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
        if ( v == btnSettings ) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else if ( v == btnCamera ) {
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);
        }
    }

    private void init (){

        btnSettings.setOnClickListener(this);
        btnCamera.setOnClickListener(this);



    }


}
