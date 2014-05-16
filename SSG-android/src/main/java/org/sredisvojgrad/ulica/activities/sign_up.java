package org.sredisvojgrad.ulica.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.turbomanage.storm.DatabaseHelper;

import org.sredisvojgrad.ulica.Database.DbFactory;
import org.sredisvojgrad.ulica.Database.ssgDatabaseHelper;
import org.sredisvojgrad.ulica.R;
import org.sredisvojgrad.ulica.api.EmailLoginService;
import org.sredisvojgrad.ulica.api.GetCitiesAndCategories;
import org.sredisvojgrad.ulica.api.SsgCommunicatorInterface;
import org.sredisvojgrad.ulica.entity.user;
import org.sredisvojgrad.ulica.model.SyncData;

import java.util.Objects;

import static junit.framework.Assert.assertEquals;

public class sign_up extends ActionBarActivity implements SsgCommunicatorInterface, View.OnClickListener {

    Button btnGradovi;
    private GetCitiesAndCategories en;
    private Button btnBack;
    private EditText eTName;
    private EditText eTSurname;
    private EditText eTEmail;
    private EditText eTPassword;
    private Button btnSignUp;
    private ListView listViewCities;
    String passedVar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        listViewCities = (ListView) findViewById(R.id.listViewCities);
        btnGradovi = (Button) findViewById(R.id.btnGradovi);
        eTName = (EditText) findViewById(R.id.eTName);
        eTSurname = (EditText) findViewById(R.id.eTSurname);
        eTEmail = (EditText) findViewById(R.id.eTEmail);
        eTPassword = (EditText) findViewById(R.id.eTPassword);
        en = new GetCitiesAndCategories(this, this);
        en.getCitiesAndCategories();
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnBack = (Button) findViewById(R.id.btnBack);
        passedVar = getIntent().getStringExtra(CitiesActivity.ID_EXTRA);
        btnGradovi.setText(passedVar);

        init();

    }


    @Override
    public void onClick(View v) {
        if (v == btnBack) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (v == btnSignUp) {

            final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if ((btnGradovi.getText().toString()).isEmpty() || (eTName.getText().toString()).isEmpty() || (eTSurname.getText().toString()).isEmpty() || (eTPassword.getText().toString()).isEmpty() || (eTEmail.getText().toString()).isEmpty()) {
                Toast.makeText(this, "Please fill all information.", Toast.LENGTH_SHORT).show();

            } else {

                user newUser = new user();
                String email = eTEmail.getText().toString();
                if (email.matches(emailPattern)) {
                    newUser.setEmail(email);
                    String name = eTName.getText().toString();
                    newUser.setName(name);
                    String lastname = eTSurname.getText().toString();
                    newUser.setLastname(lastname);
                    String password = eTPassword.getText().toString();
                    newUser.setPassword(password);
                    String city = btnGradovi.getText().toString();
                    newUser.setUserCity(city);
                    EmailLoginService loginService = new EmailLoginService(this);
                    loginService.login(newUser);
                    Toast.makeText(this, "Please verify your email address.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Please enter valide email address.", Toast.LENGTH_SHORT).show();
                }

               /*   openDatabase();
               userDao dao=new userDao(getApplicationContext());
              // userTable ut= new userTable();
               long newUserId = dao.insert(newUser);
               newUser=dao.get(newUserId);
               assertEquals(newUserId,newUser.getUserId());
              // DatabaseHelper dbH= DbFactory.getDatabaseHelper(getApplicationContext());*/

            }

        } else if (v == btnGradovi) {
            Intent intent = new Intent(this, CitiesActivity.class);
            startActivity(intent);
        }
    }


    private void init() {

        btnBack.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnGradovi.setOnClickListener(this);


    }

    private void openDatabase() {
        DatabaseHelper dbHelper = DbFactory.getDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        assertEquals(ssgDatabaseHelper.DB_VERSION, db.getVersion());
        // wipe database
        //  dbHelper.onUpgrade(db, ssgDatabaseHelper.DB_VERSION, ssgDatabaseHelper.DB_VERSION);
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
