package com.dungnh8.truyen_ngon_tinh.controller.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dungnh8.truyen_ngon_tinh.controller.holder.BookHolder;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    public static final String TAG = BookAdapter.class.getSimpleName();

    private List<Book> books;
    private LayoutInflater mInflater;
    private Context context;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (books == null) {
            return 0;
        }
        return books.size();
    }

    @Override
    public Book getItem(int position) {
        return books.get(position);
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
        Book book = getItem(position);
        BookHolder holder;
        if (convertView == null) {
            holder = new BookHolder(context, book);
            holder.initHolder(parent, convertView, position, mInflater);
        } else {
            holder = (BookHolder) convertView.getTag();
        }
        holder.setElements(book);
        convertView = holder.getConvertView();
        return convertView;
    }
}