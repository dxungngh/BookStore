package com.dungnh8.truyen_ngon_tinh.controller.fragment.tab;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.business.BookBusiness;
import com.dungnh8.truyen_ngon_tinh.controller.adapter.BookAdapter;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;

import java.util.ArrayList;
import java.util.List;

public class HistoryTabFragment extends Fragment {
    private static final String TAG = HistoryTabFragment.class.getSimpleName();

    private Handler handler;
    private ListView booksListView;
    private BookAdapter adapter;

    private BookBusiness bookBusiness;

    private List<Book> bookList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_list, container, false);
        initData();
        setComponentViews(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getHistoryBooks();
    }

    private void drawBooksList() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (adapter == null) {
                        adapter = new BookAdapter(HistoryTabFragment.this.getActivity(), bookList);
                        booksListView.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "drawBooksList", e);
                }
            }
        });
    }

    private void getHistoryBooks() {
        bookList = bookBusiness.getHistoryBooks();
        drawBooksList();
    }

    private void initData() {
        bookBusiness = (BookBusiness) ServiceRegistry.getService(BookBusiness.TAG);
        handler = new Handler();
    }

    private void setComponentViews(View rootView) {
        booksListView = (ListView) rootView.findViewById(R.id.fragment_book_list_books);
    }
}