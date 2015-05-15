package com.dungnh8.truyen_ngon_tinh.controller.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.utils.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BookHolder extends BaseContentHolder {
    private static final String TAG = BookHolder.class.getSimpleName();

    private static BookHolder holder;

    private Context context;
    private ImageView avatarImageView;
    private TextView titleTextView, authorTextView, catNameTextView;

    private Book book;

    public BookHolder(Context context, Book book) {
        this.context = context;
        this.book = book;
        holder = this;
    }

    private void assignComponentView(View rowView) {
        avatarImageView = (ImageView) rowView.findViewById(R.id.row_book_avatar);
        titleTextView = (TextView) rowView.findViewById(R.id.row_book_title);
        authorTextView = (TextView) rowView.findViewById(R.id.row_book_author);
        catNameTextView = (TextView) rowView.findViewById(R.id.row_book_cat_name);
    }

    private void drawComponentView() {
        ImageLoader.getInstance().displayImage(book.getThumbnail(), avatarImageView, ImageLoaderUtil.getDisplayOptions());
        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        catNameTextView.setText(book.getCatName());
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