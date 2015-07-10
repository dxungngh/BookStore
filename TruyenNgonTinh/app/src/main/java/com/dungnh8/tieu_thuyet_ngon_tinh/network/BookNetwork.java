package com.dungnh8.tieu_thuyet_ngon_tinh.network;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dungnh8.tieu_thuyet_ngon_tinh.ServiceRegistry;
import com.dungnh8.tieu_thuyet_ngon_tinh.config.BookConfig;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.Book;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.Chap;
import com.dungnh8.tieu_thuyet_ngon_tinh.listener.OnGetBookDetailListener;
import com.dungnh8.tieu_thuyet_ngon_tinh.listener.OnGetBooksFromServerListener;
import com.dungnh8.tieu_thuyet_ngon_tinh.parser.BookParser;

import java.util.List;

public class BookNetwork extends BaseNetwork {
    public static final String TAG = BookNetwork.class.getSimpleName();

    private MyVolley volley;
    private BookParser parser;

    public BookNetwork() {
        volley = (MyVolley) ServiceRegistry.getService(MyVolley.TAG);
        parser = (BookParser) ServiceRegistry.getService(BookParser.TAG);
    }

    public void getBookDetail(String api, int index, final OnGetBookDetailListener listener) {
        String url = String.format("%s&index=%d", api, index);
        Log.i(TAG, url);
        MyRequest request = new MyRequest(
            MyRequest.Method.GET,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<Chap> chaps = parser.parseChaps(response);
                        listener.onSuccess(chaps);
                    } catch (Exception e) {
                        listener.onFailed(e);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onFailed(error);
                }
            });
        volley.addToRequestQueue(request);
    }

    public void getBooksFromServer(boolean isSearching, String keyword, int bookType, int pageIndex, int limit, final OnGetBooksFromServerListener listener) {
        String url = getUrl(isSearching, keyword, bookType, pageIndex, limit);
        Log.i(TAG, url);
        MyRequest request = new MyRequest(
            MyRequest.Method.GET,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<Book> books = parser.parseListOfBooks(response);
                        listener.onSuccess(books);
                    } catch (Exception e) {
                        listener.onFailed(e);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onFailed(error);
                }
            });
        volley.addToRequestQueue(request);
    }

    public void getHotBooks(int pageIndex, int limit, final OnGetBooksFromServerListener listener) {
        String url = getUrl(String.format("api?page=list-story&start=%d&limit=%d&type=hot", pageIndex * limit, limit));
        Log.i(TAG, url);
        MyRequest request = new MyRequest(
            MyRequest.Method.GET,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<Book> books = parser.parseListOfBooks(response);
                        listener.onSuccess(books);
                    } catch (Exception e) {
                        listener.onFailed(e);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onFailed(error);
                }
            });
        volley.addToRequestQueue(request);
    }

    private String getUrl(boolean isSearching, String keyword, int bookType, int pageIndex, int limit) {
        String url = BookConfig.BOOK_TYPE_LINK[bookType];
        if (!isSearching) {
            if (!TextUtils.isEmpty(url)) {
                url += String.format("&start=%d&limit=%d", pageIndex * limit, limit);
            } else {
                url = getUrl(String.format("api?page=list-story&start=%d&limit=%d&type=new", pageIndex * limit, limit));
            }
        } else {
            url = getUrl(String.format("api?page=search&start=%d&limit=%d&keyword=%s", pageIndex * limit, limit, keyword));
        }
        return url;
    }
}