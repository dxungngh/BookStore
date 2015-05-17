package com.dungnh8.truyen_ngon_tinh.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dungnh8.truyen_ngon_tinh.database.model.Book;

public class BookDetailFragment extends Fragment {
    private static final String TAG = BookDetailFragment.class.getSimpleName();

    private Book book;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}