package com.dungnh8.truyen_ngon_tinh.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.utils.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BookDetailFragment extends Fragment {
    private static final String TAG = BookDetailFragment.class.getSimpleName();

    private Book book;

    private ImageView avatarImageView;
    private TextView titleTextView, authorTextView, catNameTextView, shortContentTextView;

    public static BookDetailFragment newInstance(Book book) {
        BookDetailFragment fragment = new BookDetailFragment();
        fragment.book = book;
        return fragment;
    }

    public BookDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        setComponentViews(view);
        drawComponentViews();
        return view;
    }

    private void drawComponentViews() {
        ImageLoader.getInstance().displayImage(book.getThumbnail(), avatarImageView, ImageLoaderUtil.getDisplayOptions());
        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        catNameTextView.setText(book.getCatName());
        shortContentTextView.setText(book.getDescription());
    }

    private void setComponentViews(View rootView) {
        avatarImageView = (ImageView) rootView.findViewById(R.id.fragment_book_detail_image);
        titleTextView = (TextView) rootView.findViewById(R.id.fragment_book_detail_title);
        authorTextView = (TextView) rootView.findViewById(R.id.fragment_book_detail_author);
        catNameTextView = (TextView) rootView.findViewById(R.id.fragment_book_detail_cat_name);
        shortContentTextView = (TextView) rootView.findViewById(R.id.fragment_book_detail_short_content);
    }
}