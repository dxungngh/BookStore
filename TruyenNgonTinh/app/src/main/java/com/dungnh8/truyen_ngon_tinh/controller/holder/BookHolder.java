package com.dungnh8.truyen_ngon_tinh.controller.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.utils.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BookHolder extends BaseContentHolder {
    private static final String TAG = BookHolder.class.getSimpleName();

    private static BookHolder holder;

    private Context context;
    private ImageView avatar;

    private Book book;

    public BookHolder(Context context, Book book) {
        this.context = context;
        this.book = book;
        holder = this;
    }

    private void assignComponentView(View rowView) {
        avatar = (ImageView) rowView.findViewById(R.id.row_book_avatar);
    }

    private void drawComponentView() {
        ImageLoader.getInstance().displayImage(book.getAvatar(), avatar, ImageLoaderUtil.getDisplayOptions());
    }

    @Override
    public void initHolder(ViewGroup parent, View rowView, int position,
                           LayoutInflater layoutInflater) {
        rowView = layoutInflater.inflate(R.layout.row_book, parent, false);
        assignComponentView(rowView);
        setConvertView(rowView);
        setALlListeners();
        rowView.setTag(holder);
    }

    private void setALlListeners() {
    }

    @Override
    public void setElements(Object obj) {
        book = (Book) obj;
        drawComponentView();
    }
}