package com.dungnh8.truyen_ngon_tinh.network;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.config.BookConfig;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetBooksFromServerListener;
import com.dungnh8.truyen_ngon_tinh.parser.BookParser;

import java.util.List;

public class BookNetwork extends BaseNetwork {
    public static final String TAG = BookNetwork.class.getSimpleName();

    private MyVolley volley;
    private BookParser parser;

    public BookNetwork() {
        volley = (MyVolley) ServiceRegistry.getService(MyVolley.TAG);
        parser = (BookParser) ServiceRegistry.getService(BookParser.TAG);
    }

    public void getBooksFromServer(int bookType, int pageIndex, int limit, final OnGetBooksFromServerListener listener) {
        String url = BookConfig.BOOK_TYPE_LINK[bookType];
        if (!TextUtils.isEmpty(url)) {
            url += String.format("&start=%d&limit=%d", pageIndex * limit, limit);
        } else {
            url = getUrl(String.format("api?page=list-story&start=%d&limit=%d&type=new", pageIndex * limit, limit));
        }
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
}