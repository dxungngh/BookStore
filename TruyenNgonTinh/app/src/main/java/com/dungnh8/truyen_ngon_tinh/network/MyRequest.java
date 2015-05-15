package com.dungnh8.truyen_ngon_tinh.network;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.dungnh8.truyen_ngon_tinh.config.NetworkConfig;

public class MyRequest extends StringRequest {
    public MyRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put(HTTP.USER_AGENT, NetworkConfig.USER_AGENT);
//        return headers;
//    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        retryPolicy = new DefaultRetryPolicy(
            NetworkConfig.HTTP_REQUEST_TIMEOUT_MS,
            NetworkConfig.MAX_RETRY_NUMBER,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return super.setRetryPolicy(retryPolicy);
    }
}