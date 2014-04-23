package org.sredisvojgrad.ulica.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.sredisvojgrad.ulica.R;
import org.sredisvojgrad.ulica.model.SyncData;

import java.util.ArrayList;
import java.util.List;

public class CitiesActivity extends ActionBarActivity  {


    private ListView listViewCities;
    private ArrayAdapter<String> city_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        listViewCities=(ListView)findViewById(R.id.listViewCities);
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
        listViewCities.setAdapter(city_adapter);
    }
}
