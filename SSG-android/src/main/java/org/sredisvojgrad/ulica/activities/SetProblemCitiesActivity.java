package org.sredisvojgrad.ulica.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.sredisvojgrad.ulica.R;
import org.sredisvojgrad.ulica.model.SyncData;

import java.util.ArrayList;
import java.util.List;

public class SetProblemCitiesActivity extends ActionBarActivity {

    private ListView lVCity;
    private ArrayAdapter<String> city_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_problem_cities);
        lVCity=(ListView)findViewById(R.id.lVCity);
        lVCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v , final int position, long id) {
                // System.out.println(v+position);
                Intent intent = new Intent(SetProblemCitiesActivity.this, SetProblemActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListViewCities();
    }

    private void setListViewCities (){

        List<String> cities = new ArrayList<String>();
        for (int i=0; i< SyncData.getInstance().cities.size();i++){

            cities.add(SyncData.getInstance().cities.get(i).name);
            System.out.println(SyncData.getInstance().cities.get(i).name);

        }


        city_adapter = new ArrayAdapter<String>( this,android.R.layout.simple_list_item_1,cities);
        lVCity.setAdapter(city_adapter);
    }
}