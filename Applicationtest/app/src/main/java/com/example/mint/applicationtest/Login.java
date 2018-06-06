package com.example.mint.applicationtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends ActionBarActivity {
    EditText UsernameEt, PasswordEt;
    SharedPreferences  sp ;
    SharedPreferences.Editor editor;
    public static final String MEMBRE_ID = "member_id";
    public static final String KEY_ID = "key_id";
    private static final String Url = "http://www.zp10290.tld.122.155.18.18.no-domain.name/select_member.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences(MEMBRE_ID, Context.MODE_PRIVATE);
        editor = sp.edit();
        UsernameEt = (EditText) findViewById(R.id.etUsername);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
    }


    public void OnLogin(View view) {
        final String username = UsernameEt.getText().toString();
        final String password = PasswordEt.getText().toString();
        /*String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);*/


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                       /* String name = object.getString("ricename");
                        String price = object.getString("price");
                        String url = object.getString("img");*/
                       String id = object.getString("memberid");
                        String user = object.getString("username");
                        String pass = object.getString("password");
                        //Log.d("login",user + " "+ pass);
                        if (username.equals(user) && password.equals(pass)) {
                            startActivity(new Intent(Login.this, MainActivity.class));
                            editor.putString(KEY_ID,id);
                            editor.commit();
                            Log.d("id",id);
                            finish();
                            break;
                        } /*else{
                            UsernameEt.setText("user error");
                            break;
                        }*/
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(jsonArrayRequest);
    }
}

