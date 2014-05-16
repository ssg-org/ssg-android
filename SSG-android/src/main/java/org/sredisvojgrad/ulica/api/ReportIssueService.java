package org.sredisvojgrad.ulica.api;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.sredisvojgrad.ulica.entity.issue;

import java.security.KeyStore;
import java.util.HashMap;

/**
 * Created by Home on 16.5.2014..
 */
public class ReportIssueService {

    private Context activity;
    //private  BaseJsonHttpResponseHandler<JsonObject> inter;
    private SsgCommunicatorInterface communicatorInterface;

    public ReportIssueService(Context activity) {
        this.activity = activity;
        //this.communicatorInterface=communication;
    }


    public void reportIssue(issue i) {

        HashMap<String, String> paramsHashMap = new HashMap<String, String>();
        paramsHashMap.put("ts", "12312312");

        paramsHashMap.put("title", i.getTitle());
        paramsHashMap.put("description", i.getDescription());
        paramsHashMap.put("city_id", i.getCity_id());
        paramsHashMap.put("category_id", i.getCategory_id());
        paramsHashMap.put("location_lat", i.getLocation_lat());
        paramsHashMap.put("location_lng", i.getLocation_lng());

        //Set params
        RequestParams params = new RequestParams();

        String signature = SsgAPI.buildSignature(paramsHashMap);
        System.out.println("SIGNATURE:" + signature);
//dodati da za city uzima id
        params.put("title", i.getTitle());
        params.put("description", i.getDescription());
        params.put("city_id", i.getCity_id());
        params.put("category_id", i.getCategory_id());
        params.put("location_lat", i.getLocation_lat());
        params.put("location_lng", i.getLocation_lng());
        params.put("ts", "12312312");
        params.put("signature", signature);


        AsyncHttpClient client = new AsyncHttpClient();
        String url = SsgAPI.getHostName() + "/issues";

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


        client.post(activity, url, params, new BaseJsonHttpResponseHandler<JSONObject>() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawResponse, JSONObject response) {

                String document_string = null;
                try {
                    document_string = response.getString("document");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("Service response" + document_string);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, JSONObject errorResponse) {

                System.out.println("Service error: " + statusCode + " raw  data: " + rawData);

            }


            @Override
            protected JSONObject parseResponse(String responseBody) throws Throwable {

                return new JSONObject(responseBody);
            }
        });
    }

}
