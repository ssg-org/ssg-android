package org.sredisvojgrad.ulica.api;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sredisvojgrad.ulica.model.Categories;
import org.sredisvojgrad.ulica.model.City;
import org.sredisvojgrad.ulica.model.SyncData;

import java.security.KeyStore;
import java.util.HashMap;

/**
 * Created by harisdautovic on 22/04/14.
 */
public class GetCitiesAndCategories {


    private Context activity;
    //private  BaseJsonHttpResponseHandler<JsonObject> inter;
    private SsgCommunicatorInterface communicatorInterface;

    public GetCitiesAndCategories(Context activity, SsgCommunicatorInterface communication) {
        this.activity = activity;
        this.communicatorInterface = communication;
    }


    public void getCitiesAndCategories() {


        HashMap<String, String> paramsHashMap = new HashMap<String, String>();
        paramsHashMap.put("ts", "12312312");

        //Set params
        RequestParams params = new RequestParams();

        String signature = SsgAPI.buildSignature(paramsHashMap);
        System.out.println("SIGNATURE:" + signature);
        params.put("signature", signature);
        params.put("ts", "12312312");

        AsyncHttpClient client = new AsyncHttpClient();
        String url = SsgAPI.getHostName() + "/info";
        client.setBasicAuth("username", "pass");

        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client.setSSLSocketFactory(sf);
        } catch (Exception e) {

            System.out.println("Error ssl :" + e);
        }


        client.get(activity, url, params, new BaseJsonHttpResponseHandler<JSONObject>() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawResponse, JSONObject response) {

                String document_string = null;
                try {
                    document_string = response.getString("document");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject document_object = null;
                try {
                    document_object = new JSONObject(document_string);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray cities_array = null;
                JSONArray categories_array = null;
                try {
                    cities_array = document_object.getJSONArray("cities");
                    categories_array = document_object.getJSONArray("categories");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Parse cities
                for (int i = 0; i < cities_array.length(); i++) {

                    JSONObject city_object = null;
                    try {
                        city_object = cities_array.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        City city = new City();
                        city.name = city_object.getString("name");

                        SyncData.getInstance().cities.add(city);
                        //System.out.println(name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                //parse categories
                for (int i = 0; i < categories_array.length(); i++) {

                    JSONObject category_object = null;
                    try {
                        category_object = categories_array.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {

                        Categories category = new Categories();
                        category.name = category_object.getString("name");
                        category.color = category_object.getString("color");
                        category.icon = category_object.getString("icon");
                        category.id = category_object.getInt("id");


                        if (!category_object.getString("parent_id").equals("null")) {
                            category.parent_id = Integer.valueOf(category_object.getInt("parent_id"));
                        }

                        SyncData.getInstance().categories.add(category);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                for (int i = 0; i < SyncData.getInstance().categories.size(); i++) {

                    System.out.println(SyncData.getInstance().categories.get(i).toString());

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, JSONObject errorResponse) {
                System.out.println("Service error: " + statusCode + " header  data: " + errorResponse);
            }

            @Override
            protected JSONObject parseResponse(String responseBody) throws Throwable {

                return new JSONObject(responseBody);
            }
        });
    }
}
