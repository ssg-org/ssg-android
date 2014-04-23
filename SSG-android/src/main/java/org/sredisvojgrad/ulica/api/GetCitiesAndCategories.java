package org.sredisvojgrad.ulica.api;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sredisvojgrad.ulica.model.City;
import org.sredisvojgrad.ulica.model.SyncData;

import java.util.HashMap;

/**
 * Created by harisdautovic on 22/04/14.
 */
public class GetCitiesAndCategories {


    private Context activity;
    //private  BaseJsonHttpResponseHandler<JsonObject> inter;
    private SsgCommunicatorInterface communicatorInterface;

    public GetCitiesAndCategories(Context activity,SsgCommunicatorInterface communication)
    {
        this.activity=activity;
        this.communicatorInterface=communication;
    }




    public void getCitiesAndCategories (){


        HashMap<String,String> paramsHashMap = new HashMap<String, String>();
        paramsHashMap.put("ts","12312312");

        //Set params
        RequestParams params = new RequestParams();

        String signature = SsgAPI.buildSignature(paramsHashMap);

        System.out.println("SIGNATURE:"+signature);
        params.put("signature", signature);
        params.put("ts", "12312312");



        AsyncHttpClient client = new AsyncHttpClient();
        String url = SsgAPI.getHostName()+"/info";

        client.setBasicAuth("username","pass");



        client.get(activity,url,params,new BaseJsonHttpResponseHandler<JSONObject>() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawResponse, JSONObject response) {

                String document_string = null;
                try {
                    document_string = response.getString("document");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject document_object=null;
                try {
                     document_object = new JSONObject(document_string);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray cities_array=null;
                try {
                   cities_array = document_object.getJSONArray("cities");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<cities_array.length();i++){

                    JSONObject city_object = null;
                    try {
                        city_object = cities_array.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        String name = city_object.getString("name");
                        City city = new City();
                        city.name=name;

                        SyncData.getInstance().cities.add(city);
                        System.out.println(name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }




            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, JSONObject errorResponse) {

            }

            @Override
            protected JSONObject parseResponse(String responseBody) throws Throwable {

                return new JSONObject(responseBody);
            }
        });
    }
}
