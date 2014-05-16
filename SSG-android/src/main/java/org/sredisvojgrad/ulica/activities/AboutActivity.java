package org.sredisvojgrad.ulica.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.sredisvojgrad.ulica.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class AboutActivity extends RoboActivity implements View.OnClickListener {

    @InjectView(R.id.textView)
    private TextView textView;
    @InjectView(R.id.button)
    private Button button;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getActionBar().setHomeButtonEnabled(true);
        init();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about, menu);
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
        if (v == button) {
            Intent intent = new Intent(AboutActivity.this, SetProblemActivity.class);
            startActivity(intent);
        }
    }

    private void init() {

        button.setOnClickListener(this);

    }
}



