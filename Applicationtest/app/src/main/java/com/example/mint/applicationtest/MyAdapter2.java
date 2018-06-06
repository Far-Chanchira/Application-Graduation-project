package com.example.mint.applicationtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Far on 26/8/2560.
 */

public class MyAdapter2 extends ArrayAdapter {
    private LayoutInflater inflater;
    Context context;

    public MyAdapter2(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.inflater = ((Activity) context).getLayoutInflater();
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_layout, null);
        }
        final JSONObject data = (JSONObject) getItem(position);
        final TextView riceName = (TextView) convertView.findViewById(R.id.txtTitle);
        final TextView price = (TextView) convertView.findViewById(R.id.txtDescription);
        final ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        final TextView id = (TextView)convertView.findViewById(R.id.dam_light_id);

        try {
            riceName.setText(data.getString("ricename"));
            price.setText(data.getString("price"));
            id.setText(data.getString("id"));
            Glide.with(context).load("http://www.zp10290.tld.122.155.18.18.no-domain.name/admin/rice/myfile/" + data.getString("img")).asBitmap().into(icon);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // เปิดหน้าใหม่ พร้อมอ่านค่าของแต่ละวิว และส่งผ่านไปในอินเท็น เพือให้หน้าโชว รับค่าไปแสดงในแต่ละวิว
                Intent open_show_activity = new Intent(context, Show.class);
                Bitmap bitmap = ((BitmapDrawable) icon.getDrawable()).getBitmap();
                open_show_activity.putExtra("ricename", riceName.getText());
                open_show_activity.putExtra("price", price.getText());
                open_show_activity.putExtra("image", bitmap);
                open_show_activity.putExtra("id",id.getText());
                try {
                    open_show_activity.putExtra("remark",data.getString("remarkable"));
                    open_show_activity.putExtra("general",data.getString("general"));
                    open_show_activity.putExtra("area",data.getString("area"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //เปิดหน้า ใหม่
                context.startActivity(open_show_activity);

            }
        });
        return convertView;
    }
}