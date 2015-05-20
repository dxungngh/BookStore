package com.dungnh8.truyen_ngon_tinh.controller.fragment.tab;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.business.BookBusiness;
import com.dungnh8.truyen_ngon_tinh.config.ExtrasConfig;
import com.dungnh8.truyen_ngon_tinh.controller.adapter.BookAdapter;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetBooksFromServerListener;

import java.util.ArrayList;
import java.util.List;

public class NewBookTabFragment extends Fragment {
    private static final String TAG = NewBookTabFragment.class.getSimpleName();

    private Handler handler;
    private ListView booksListView;
    private BookAdapter adapter;

    private BookBusiness bookBusiness;

    private List<Book> bookList = new ArrayList<>();
    private int selectedBookType = 0;
    private int preLast;
    private int currentPage = 0;

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
        setAllListeners();
        drawBooksList(bookList, true);
        getBooksFromServer();
        return rootView;
    }

    private void drawBooksList(final List<Book> books, final boolean isFirst) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!isFirst) {
                        bookList.addAll(books);
                    }
                    adapter = new BookAdapter(NewBookTabFragment.this.getActivity(), bookList);
                    booksListView.setAdapter(adapter);
                } catch (Exception e) {
                    Log.e(TAG, "drawBooksList", e);
                }
            }
        });
    }

    private void getBooksFromServer() {
        bookBusiness.getBooksFromServer(false, null, selectedBookType, currentPage,
            new OnGetBooksFromServerListener() {
                @Override
                public void onSuccess(List<Book> result) {
                    drawBooksList(result, false);
                    currentPage++;
                }

                @Override
                public void onFailed(Throwable error) {
                    Log.e(TAG, "getBooksFromServer", error);
                }
            });
    }

    private void initData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            selectedBookType = bundle.getInt(ExtrasConfig.SELECTED_BOOK_TYPE, 0);
        }
        Log.i(TAG, "selectedBookType: " + selectedBookType);

        bookBusiness = (BookBusiness) ServiceRegistry.getService(BookBusiness.TAG);

        handler = new Handler();
    }

    private void setAllListeners() {
        setBookListScrollToBottomListener();
    }

    private void setBookListScrollToBottomListener() {
        booksListView.setOnScrollListener(
            new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    final int lastItem = firstVisibleItem + visibleItemCount;
                    if (lastItem == totalItemCount) {
                        if (preLast != lastItem) {
                            preLast = lastItem;
                            getBooksFromServer();
                        }
                    }
                }
            }
        );
    }

    private void setComponentViews(View rootView) {
        booksListView = (ListView) rootView.findViewById(R.id.fragment_book_list_books);
    }
}