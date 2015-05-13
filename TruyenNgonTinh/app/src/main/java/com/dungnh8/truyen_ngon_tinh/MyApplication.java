package com.dungnh8.truyen_ngon_tinh;

import android.app.Application;
import android.util.Log;

import com.dungnh8.truyen_ngon_tinh.business.BookBusiness;
import com.dungnh8.truyen_ngon_tinh.network.BookNetwork;
import com.dungnh8.truyen_ngon_tinh.network.MyVolley;
import com.dungnh8.truyen_ngon_tinh.parser.BookParser;
import com.dungnh8.truyen_ngon_tinh.utils.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();

    private void initDisplayConfig() {
        int memory = (int) Runtime.getRuntime().maxMemory() / 4;
        Log.i(TAG, "memory: " + memory);
        ImageLoader.getInstance().init(ImageLoaderUtil.getConfiguration(this, memory));
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initDisplayConfig();
        registerServices();
    }

    private void registerBusinesses() {
        ServiceRegistry.registerService(BookBusiness.TAG, new BookBusiness());
    }

    private void registerDataSources() {
    }

    private void registerNetworks() {
        ServiceRegistry.registerService(MyVolley.TAG, new MyVolley(getApplicationContext()));
        ServiceRegistry.registerService(BookNetwork.TAG, new BookNetwork());
    }

    private void registerParsers() {
        ServiceRegistry.registerService(BookParser.TAG, new BookParser());
    }

    private void registerServices() {
        registerParsers();
        registerNetworks();
        registerDataSources();
        registerBusinesses();
    }
}