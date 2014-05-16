package org.sredisvojgrad.ulica.api;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyStore;
import java.util.HashMap;

/**
 * Created by Home on 15.5.2014..
 */
public class LoginService {


    private Context activity;
    //private  BaseJsonHttpResponseHandler<JsonObject> inter;
    private SsgCommunicatorInterface communicatorInterface;

    public LoginService(Context activity) {
        this.activity = activity;
        //this.communicatorInterface=communication;
    }


    public void login(String email, String password) {

        HashMap<String, String> paramsHashMap = new HashMap<String, String>();
        paramsHashMap.put("ts", "12312312");

        paramsHashMap.put("password", password);
        paramsHashMap.put("email", email);

        //Set params
        RequestParams params = new RequestParams();

        String signature = SsgAPI.buildSignature(paramsHashMap);
        System.out.println("SIGNATURE:" + signature);
//dodati da za city uzima id
        params.put("password", password);
        params.put("email", email);
        params.put("ts", "12312312");
        params.put("signature", signature);

        AsyncHttpClient client = new AsyncHttpClient();
        String url = SsgAPI.getHostName() + "/sessions";
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

                System.out.println("Service response" + document_string);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, JSONObject errorResponse) {



                System.out.println("Service errorrrrrr: " + statusCode + " raw  data: " + e.toString());

            }


            @Override
            protected JSONObject parseResponse(String responseBody) throws Throwable {

                return new JSONObject(responseBody);
            }
        });
    }
}


