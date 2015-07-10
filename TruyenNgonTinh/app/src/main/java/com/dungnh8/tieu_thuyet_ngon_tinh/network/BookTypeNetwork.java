package com.dungnh8.tieu_thuyet_ngon_tinh.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dungnh8.tieu_thuyet_ngon_tinh.ServiceRegistry;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.BookType;
import com.dungnh8.tieu_thuyet_ngon_tinh.listener.OnGetBookTypesListener;
import com.dungnh8.tieu_thuyet_ngon_tinh.parser.BookTypeParser;

import java.util.List;

public class BookTypeNetwork extends BaseNetwork {
    public static final String TAG = BookTypeNetwork.class.getSimpleName();

    private MyVolley volley;

    private BookTypeParser parser;

    public BookTypeNetwork() {
        volley = (MyVolley) ServiceRegistry.getService(MyVolley.TAG);
        parser = (BookTypeParser) ServiceRegistry.getService(BookTypeParser.TAG);
    }

    public void getBookTypes(final OnGetBookTypesListener listener) {
        String url = getUrl("api?page=menu");
        MyRequest request = new MyRequest(
            MyRequest.Method.GET,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        List<BookType> bookTypeList = parser.parseListOfBookTypes(response);
                        listener.onSuccess(bookTypeList);
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