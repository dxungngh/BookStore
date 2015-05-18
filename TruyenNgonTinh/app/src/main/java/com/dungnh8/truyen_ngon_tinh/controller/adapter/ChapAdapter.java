package com.dungnh8.truyen_ngon_tinh.controller.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dungnh8.truyen_ngon_tinh.controller.holder.ChapHolder;
import com.dungnh8.truyen_ngon_tinh.database.model.Chap;

import java.util.List;

public class ChapAdapter extends BaseAdapter {
    public static final String TAG = ChapAdapter.class.getSimpleName();

    private List<Chap> chaps;
    private LayoutInflater mInflater;
    private Context context;

    public ChapAdapter(Context context, List<Chap> chaps) {
        this.context = context;
        this.chaps = chaps;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (chaps == null) {
            return 0;
        }
        return chaps.size();
    }

    @Override
    public Chap getItem(int position) {
        return chaps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Chap chap = getItem(position);
        ChapHolder holder;
        if (convertView == null) {
            holder = new ChapHolder(context, chap);
            holder.initHolder(parent, convertView, position, mInflater);
        } else {
            holder = (ChapHolder) convertView.getTag();
        }
        holder.setElements(chap);
        convertView = holder.getConvertView();
        return convertView;
    }
}