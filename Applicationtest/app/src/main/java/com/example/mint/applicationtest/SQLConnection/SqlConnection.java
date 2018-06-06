package com.example.mint.applicationtest.SQLConnection;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class SqlConnection {
    public static int insertUser(String date ,String riceName ,String quantity ,String price ,String idUser,String idRice ){  //------------ Method to insert data ---------------//
                                  //TODO เพิ่มตัวแปรเป็นหกตัว
        InputStream is = null;
        String js_result = "";

        try{

             //Define Data

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("orderdate",date));
            nameValuePairs.add(new BasicNameValuePair("list",riceName));
            nameValuePairs.add(new BasicNameValuePair("number",quantity));
            nameValuePairs.add(new BasicNameValuePair("price",price));
            nameValuePairs.add(new BasicNameValuePair("memberid",idUser));
            nameValuePairs.add(new BasicNameValuePair("id",idRice));


            // Connect Server

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.zp10290.tld.122.155.18.18.no-domain.name/insert_data.php"); // https://10.0.2.2/
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.d("log_err", "Error in http connection " + e.toString());
            return -1;
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            js_result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
            return -1;
        }
        int id = -1;
        try {

            final JSONArray jArray = new JSONArray(js_result);
            for (int i = 0; i < jArray.length(); i++) {
                String[] comment = new String[3];
                JSONObject jo = jArray.getJSONObject(i);
                id = Integer.parseInt((jo.get("id").toString()));
                //tv_res.append(hn+","+name+","+age+","+date_serv+"\n");
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
            return -1;
        }

        return id;
    }
}
