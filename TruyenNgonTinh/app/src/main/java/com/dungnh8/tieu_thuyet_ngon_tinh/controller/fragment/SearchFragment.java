package com.dungnh8.tieu_thuyet_ngon_tinh.controller.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.dungnh8.tieu_thuyet_ngon_tinh.R;
import com.dungnh8.tieu_thuyet_ngon_tinh.ServiceRegistry;
import com.dungnh8.tieu_thuyet_ngon_tinh.business.BookBusiness;
import com.dungnh8.tieu_thuyet_ngon_tinh.controller.adapter.BookAdapter;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.Book;
import com.dungnh8.tieu_thuyet_ngon_tinh.listener.OnGetBooksFromServerListener;
import com.dungnh8.tieu_thuyet_ngon_tinh.utils.KeyboardUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private static final String TAG = SearchFragment.class.getSimpleName();

    private Handler handler;
    private ListView booksListView;
    private EditText searchEditText;
    private BookAdapter adapter;

    private BookBusiness bookBusiness;

    private List<Book> bookList = new ArrayList<>();
    private int preLast;
    private int currentPage = 0;
    String keyword;
    private boolean isSearching = false;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    public SearchFragment() {
        initData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        initData();
        setComponentViews(rootView);
        setAllListeners();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        isSearching = false;
        KeyboardUtil.hideSoftKeyboardOfEditText(getActivity(), searchEditText);
    }

    private void drawBooksList(final List<Book> books) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    bookList.addAll(books);
                    if (adapter == null) {
                        adapter = new BookAdapter(SearchFragment.this.getActivity(), bookList);
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

    private void getBooksFromServer() {
        bookBusiness.getBooksFromServer(isSearching, keyword, 0, currentPage,
            new OnGetBooksFromServerListener() {
                @Override
                public void onSuccess(List<Book> result) {
                    drawBooksList(result);
                    currentPage++;
                }

                @Override
                public void onFailed(Throwable error) {
                    Log.e(TAG, "getBooksFromServer", error);
                }
            });
    }

    private void initData() {
        bookBusiness = (BookBusiness) ServiceRegistry.getService(BookBusiness.TAG);

        handler = new Handler();
    }

    private void performSearch() {
        currentPage = 0;
        bookList.clear();
        keyword = searchEditText.getText().toString().trim();
        isSearching = true;
        getBooksFromServer();
    }

    private void setAllListeners() {
        setBookListScrollToBottomListener();
        setSearchListener();
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
        searchEditText = (EditText) rootView.findViewById(R.id.fragment_book_list_search);
    }

    private void setSearchListener() {
        searchEditText.setOnEditorActionListener(
            new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    String keyword = searchEditText.getText().toString().trim();
                    Log.i(TAG, "actionId: " + actionId);
                    if (!TextUtils.isEmpty(keyword)) {
                        if (actionId == 0
                            || actionId == EditorInfo.IME_ACTION_SEARCH
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || actionId == EditorInfo.IME_ACTION_GO
                            || actionId == EditorInfo.IME_ACTION_NEXT) {
                            performSearch();
                            return true;
                        }
                    }
                    return false;
                }
            }
        );
    }
}