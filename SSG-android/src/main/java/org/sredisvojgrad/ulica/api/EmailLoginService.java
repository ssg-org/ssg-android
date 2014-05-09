package org.sredisvojgrad.ulica.api;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.sredisvojgrad.ulica.entity.user;

import java.util.HashMap;

/**
 * Created by Home on 9.5.2014..
 */
public class EmailLoginService {


    private Context activity;
    //private  BaseJsonHttpResponseHandler<JsonObject> inter;
    private SsgCommunicatorInterface communicatorInterface;

    public EmailLoginService(Context activity)
    {
        this.activity=activity;
        //this.communicatorInterface=communication;
    }


    public void login (user u){

        HashMap<String,String> paramsHashMap = new HashMap<String, String>();
        paramsHashMap.put("ts","12312312");

        paramsHashMap.put("password",u.getPassword());
        paramsHashMap.put("email", u.getEmail());
        paramsHashMap.put("city_id",u.getUserCity());
        paramsHashMap.put("first_name",u.getName());
        paramsHashMap.put("last_name", u.getLastname());

        //Set params
        RequestParams params = new RequestParams();

        String signature = SsgAPI.buildSignature(paramsHashMap);
        System.out.println("SIGNATURE:"+signature);
//dodati da za city uzima id
        params.put("password", u.getPassword());
        params.put("email", u.getEmail());
        params.put("city_id", u.getUserCity());
        params.put("first_name", u.getName());
        params.put("last_name",u.getLastname());
        params.put("ts","12312312");
        params.put("signature", signature);




        AsyncHttpClient client = new AsyncHttpClient();
        String url = SsgAPI.getHostName()+"/sessions/signup";

        client.setBasicAuth("username","pass");


        client.post(activity,url,params,new BaseJsonHttpResponseHandler<JSONObject>() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawResponse, JSONObject response) {

                String document_string = null;
                try {
                    document_string = response.getString("document");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("Service response"+document_string);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, JSONObject errorResponse) {

                System.out.println("Service error: " + statusCode + " raw  data: "+rawData);

            }


            @Override
            protected JSONObject parseResponse(String responseBody) throws Throwable {

                return new JSONObject(responseBody);
            }
        });
    }

}
