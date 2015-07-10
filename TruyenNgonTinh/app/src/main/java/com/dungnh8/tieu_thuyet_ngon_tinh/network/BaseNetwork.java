package com.dungnh8.tieu_thuyet_ngon_tinh.network;

import com.dungnh8.tieu_thuyet_ngon_tinh.config.NetworkConfig;

public class BaseNetwork {
    private static final String TAG = BaseNetwork.class.getSimpleName();

    public String getUrl(String link) {
        String url = NetworkConfig.HOST + link;
        return url;
    }
}
