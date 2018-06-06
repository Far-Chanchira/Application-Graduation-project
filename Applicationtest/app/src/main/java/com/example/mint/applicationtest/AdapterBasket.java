package com.example.mint.applicationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mint.applicationtest.DataClass.DataBasket;

import java.util.List;

/**
 * Created by Far on 26/8/2560.
 */

public class AdapterBasket extends BaseAdapter {
    private List<DataBasket> mDatas;
    private LayoutInflater mLayoutlnflater;
    private Context context;

    public AdapterBasket(Context context, List<DataBasket> aList) {
        this.context = context;
        mDatas = aList;
        mLayoutlnflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvPrice;
        TextView txtNum;
        TextView txtTotal;

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
        final AdapterBasket.ViewHolder holder;
        if (view == null) {
            view = mLayoutlnflater.inflate(R.layout.row_basket, viewGroup, false);
            holder = new AdapterBasket.ViewHolder();
            holder.tvName = (TextView) view.findViewById(R.id.txtName);
            holder.tvPrice = (TextView) view.findViewById(R.id.txtPrice);
            holder.txtNum = (TextView) view.findViewById(R.id.text_num);
            holder.txtTotal = (TextView) view.findViewById(R.id.txtTotal);


            view.setTag(holder);
        } else
            holder = (AdapterBasket.ViewHolder) view.getTag();
        holder.tvName.setText(mDatas.get(position).getmText1());
        holder.tvPrice.setText(mDatas.get(position).getmText2());
        return view;
    }
}