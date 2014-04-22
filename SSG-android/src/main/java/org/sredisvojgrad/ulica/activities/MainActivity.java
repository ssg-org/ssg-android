package org.sredisvojgrad.ulica.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.sredisvojgrad.ulica.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;


public class MainActivity extends RoboActivity implements  OnClickListener {


    @InjectView(R.id.txtEmail)    private EditText txtEmail;
    @InjectView(R.id.btnLogin)    private Button btnLogin;
    @InjectView(R.id.textView)    private TextView textView;
    @InjectView(R.id.btnFacebook) private Button btnFacebook;
    @InjectView(R.id.imageView)   private ImageView imageView;
    @InjectView(R.id.textView2)   private TextView textView2;
    @InjectView(R.id.btnSignup)   private Button btnSignup;
    @InjectView(R.id.txtPassword) private EditText txtPassword;

    int width,height;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
         width = size.x;
         height = size.y;


        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    public void onClick(View view) {

        if(view==btnFacebook){


        }else if(view==btnLogin){

            Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
            startActivity(intent);

        }else if(view==btnSignup){

            Intent intent = new Intent(MainActivity.this, sign_up.class);
            startActivity(intent);

        }

    }

    private void init (){

        btnFacebook.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);



    }



}
