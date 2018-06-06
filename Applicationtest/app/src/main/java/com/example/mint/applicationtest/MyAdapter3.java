package com.example.mint.applicationtest;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Far on 26/8/2560.
 */

public class MyAdapter3 extends ArrayAdapter {
    private LayoutInflater inflater;
    Context context;
    public MyAdapter3( Context context,  int resource, List objects) {
        super(context, resource, objects);
        this.context=context;
        this.inflater = ((Activity)context).getLayoutInflater();
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_news, null);
        }
        JSONObject data = (JSONObject) getItem(position);
        TextView riceName=(TextView)convertView.findViewById(R.id.txtTitle);
        TextView price=(TextView)convertView.findViewById(R.id.txtDescription);
        //ImageView icon = (ImageView)convertView.findViewById(R.id.icon);

        try {
            riceName.setText(data.getString("title"));
            price.setText(data.getString("content"));
           // Glide.with(context).load("http://www.zp10290.tld.122.155.18.18.no-domain.name/admin/rice/myfile/"+data.getString("img")).asBitmap().into(icon);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}