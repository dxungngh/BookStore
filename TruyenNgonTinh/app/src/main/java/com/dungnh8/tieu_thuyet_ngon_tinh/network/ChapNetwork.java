package com.dungnh8.tieu_thuyet_ngon_tinh.network;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dungnh8.tieu_thuyet_ngon_tinh.ServiceRegistry;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.Chap;
import com.dungnh8.tieu_thuyet_ngon_tinh.listener.OnGetChapDetailListener;
import com.dungnh8.tieu_thuyet_ngon_tinh.parser.ChapParser;

public class ChapNetwork extends BaseNetwork {
    public static final String TAG = ChapNetwork.class.getSimpleName();

    private MyVolley volley;
    private ChapParser parser;

    public ChapNetwork() {
        volley = (MyVolley) ServiceRegistry.getService(MyVolley.TAG);
        parser = (ChapParser) ServiceRegistry.getService(ChapParser.TAG);
    }

    public void getChapDetail(String api, final OnGetChapDetailListener listener) {
        Log.i(TAG, api);
        MyRequest request = new MyRequest(
            MyRequest.Method.GET,
            api,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Chap chap = parser.parse(response);
                        listener.onSuccess(chap);
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