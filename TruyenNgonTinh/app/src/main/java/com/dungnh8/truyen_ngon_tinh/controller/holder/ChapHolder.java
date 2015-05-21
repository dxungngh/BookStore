package com.dungnh8.truyen_ngon_tinh.controller.holder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.config.ExtrasConfig;
import com.dungnh8.truyen_ngon_tinh.controller.activity.ChapDetailActivity;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.database.model.Chap;

public class ChapHolder extends BaseContentHolder {
    private static final String TAG = ChapHolder.class.getSimpleName();

    private static ChapHolder holder;

    private Context context;
    private TextView titleTextView;
    private View rowView;

    private Book book;
    private Chap chap;

    public ChapHolder(Context context, Book book, Chap chap) {
        this.context = context;
        this.book = book;
        this.chap = chap;
        holder = this;
    }

    private void assignComponentView(View rowView) {
        titleTextView = (TextView) rowView.findViewById(R.id.row_chap_title);
    }

    private void drawComponentView() {
        titleTextView.setText(chap.getTitle());
    }

    @Override
    public void initHolder(ViewGroup parent, View rowView, int position,
                           LayoutInflater layoutInflater) {
        rowView = layoutInflater.inflate(R.layout.row_chap, parent, false);
        assignComponentView(rowView);
        setConvertView(rowView);
        rowView.setTag(holder);
        this.rowView = rowView;
        setALlListeners();
    }

    private void setALlListeners() {
        setRowClickListener();
    }

    @Override
    public void setElements(Object obj) {
        chap = (Chap) obj;
        drawComponentView();
    }

    private void setRowClickListener() {
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChapDetailActivity.class);
                intent.putExtra(ExtrasConfig.BOOK, book);
                intent.putExtra(ExtrasConfig.CHAP, chap);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
}