package com.dungnh8.truyen_ngon_tinh.network;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.config.BookConfig;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetBooksFromServerListener;
import com.dungnh8.truyen_ngon_tinh.parser.BookParser;

import java.util.List;

public class BookNetwork {
    public static final String TAG = BookNetwork.class.getSimpleName();

    private MyVolley volley;

    private BookParser bookParser;

    public BookNetwork() {
        volley = (MyVolley) ServiceRegistry.getService(MyVolley.TAG);
        bookParser = (BookParser) ServiceRegistry.getService(BookParser.TAG);
    }

    public void getBooksFromServer(int bookType, int pageIndex, final OnGetBooksFromServerListener listener) {
        String url = getUrl(bookType, pageIndex);
        MyRequest myRequest = new MyRequest(MyRequest.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<Book> books = bookParser.parseBooks(response);
                        listener.onSuccess(books);
                    } catch (Exception e) {
                        Log.i(TAG, "getBooksFromServer", e);
                        listener.onFailed(e);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, "getBooksFromServer", error);
                    listener.onFailed(error);
                }
            });
        volley.addToRequestQueue(myRequest);
    }

    private String getUrl(int bookType, int pageIndex) {
        if (pageIndex > 1) {
            return BookConfig.BOOK_TYPE_LINK[bookType] + "page/" + pageIndex + "/";
        }
        return BookConfig.BOOK_TYPE_LINK[bookType];
    }
}