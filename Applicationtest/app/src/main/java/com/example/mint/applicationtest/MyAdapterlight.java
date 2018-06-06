package com.example.mint.applicationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mint.applicationtest.DataClass.Datalight;

import java.util.List;

/**
 * Created by Mint on 8/6/2017.
 */

public class MyAdapterlight extends BaseAdapter {
    private List<Datalight> mDatas;
    private LayoutInflater mLayoutlnflater;
    private Context context;

    public MyAdapterlight(Context context, List<Datalight> aList) {
        this.context = context;
        mDatas = aList;
        mLayoutlnflater = LayoutInflater.from(context);

    }

    static class ViewHolder {
        TextView tvTitle;
        TextView tvDesc;
        ImageView img;
    }

    @Override
    public int getCount() {

        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, final ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = mLayoutlnflater.inflate(R.layout.row_layout, viewGroup, false);
            holder = new ViewHolder();

            holder.tvTitle = (TextView) view.findViewById(R.id.txtTitle);
            holder.tvDesc = (TextView) view.findViewById(R.id.txtDescription);
            holder.img = (ImageView) view.findViewById(R.id.icon);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        holder.tvTitle.setText(mDatas.get(position).getmText1());
        holder.tvDesc.setText(mDatas.get(position).getmText2());
        Glide.with(context).load(mDatas.get(position).getmIcon()).asBitmap().into(holder.img);
       //
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
                String a = (String) holder.tvTitle.getText();
                Toast.makeText(context, a, Toast.LENGTH_SHORT).show();
                view.getContext().startActivity(new Intent(context, Show.class));
*/
            }
        });

        return view;
    }
}
