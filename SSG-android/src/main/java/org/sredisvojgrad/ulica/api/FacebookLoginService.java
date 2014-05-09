package org.sredisvojgrad.ulica.api;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Home on 9.5.2014..
 */
public class FacebookLoginService {

    private Context activity;
    //private  BaseJsonHttpResponseHandler<JsonObject> inter;
    private SsgCommunicatorInterface communicatorInterface;

    public FacebookLoginService(Context activity)
    {
        this.activity=activity;
        //this.communicatorInterface=communication;
    }


    public void login (){

        HashMap<String,String> paramsHashMap = new HashMap<String, String>();
        paramsHashMap.put("ts","12312312");

        paramsHashMap.put("email", "fbmail");
        paramsHashMap.put("fb_id", "32");
        paramsHashMap.put("first_name", "ime");
        paramsHashMap.put("last_name", "prezime");

        //Set params
        RequestParams params = new RequestParams();

        String signature = SsgAPI.buildSignature(paramsHashMap);
        System.out.println("SIGNATURE:"+signature);

        params.put("email", "fbmail");
        params.put("fb_id", "32");
        params.put("first_name", "ime");
        params.put("last_name", "prezime");
        params.put("signature", signature);



        AsyncHttpClient client = new AsyncHttpClient();
        String url = SsgAPI.getHostName()+"/api/v1/sessions/fb_create";

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

                System.out.println("Service error" + statusCode);

            }


            @Override
            protected JSONObject parseResponse(String responseBody) throws Throwable {

                return new JSONObject(responseBody);
            }
        });
    }

}
