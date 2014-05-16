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

public class CategoriesActivity extends ActionBarActivity {

    public final static String ID_EXTRA = "string_i_need";
    private ListView lVCategories;
    private ArrayAdapter<String> categories_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        lVCategories = (ListView) findViewById(R.id.lVCategories);
        lVCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                String text = lVCategories.getItemAtPosition(position).toString().trim();
                Intent intent = new Intent(CategoriesActivity.this, SetProblemActivity.class);
                intent.putExtra(ID_EXTRA, text);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListViewCategories();
    }

    private void setListViewCategories() {

        List<String> categories = new ArrayList<String>();
        for (int i = 0; i < SyncData.getInstance().categories.size(); i++) {

            categories.add(SyncData.getInstance().categories.get(i).name);
            System.out.println(SyncData.getInstance().categories.get(i).name);

        }


        categories_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        lVCategories.setAdapter(categories_adapter);
    }


}
