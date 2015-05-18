package com.dungnh8.truyen_ngon_tinh;

import android.app.Application;
import android.util.Log;

import com.dungnh8.truyen_ngon_tinh.business.BookBusiness;
import com.dungnh8.truyen_ngon_tinh.business.BookTypeBusiness;
import com.dungnh8.truyen_ngon_tinh.business.ChapBusiness;
import com.dungnh8.truyen_ngon_tinh.database.datasource.BookDataSource;
import com.dungnh8.truyen_ngon_tinh.database.datasource.BookTypeDataSource;
import com.dungnh8.truyen_ngon_tinh.database.datasource.ChapDataSource;
import com.dungnh8.truyen_ngon_tinh.network.BookNetwork;
import com.dungnh8.truyen_ngon_tinh.network.BookTypeNetwork;
import com.dungnh8.truyen_ngon_tinh.network.ChapNetwork;
import com.dungnh8.truyen_ngon_tinh.network.MyVolley;
import com.dungnh8.truyen_ngon_tinh.parser.BookParser;
import com.dungnh8.truyen_ngon_tinh.parser.BookTypeParser;
import com.dungnh8.truyen_ngon_tinh.parser.ChapParser;
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
        ServiceRegistry.registerService(BookTypeBusiness.TAG, new BookTypeBusiness());
        ServiceRegistry.registerService(ChapBusiness.TAG, new ChapBusiness());
    }

    private void registerDataSources() {
        ServiceRegistry.registerService(BookDataSource.TAG, new BookDataSource(getApplicationContext()));
        ServiceRegistry.registerService(BookTypeDataSource.TAG, new BookTypeDataSource(getApplicationContext()));
        ServiceRegistry.registerService(ChapDataSource.TAG, new ChapDataSource(getApplicationContext()));
    }

    private void registerNetworks() {
        ServiceRegistry.registerService(MyVolley.TAG, new MyVolley(getApplicationContext()));
        ServiceRegistry.registerService(BookNetwork.TAG, new BookNetwork());
        ServiceRegistry.registerService(BookTypeNetwork.TAG, new BookTypeNetwork());
        ServiceRegistry.registerService(ChapNetwork.TAG, new ChapNetwork());
    }

    private void registerParsers() {
        ServiceRegistry.registerService(BookParser.TAG, new BookParser());
        ServiceRegistry.registerService(BookTypeParser.TAG, new BookTypeParser());
        ServiceRegistry.registerService(ChapParser.TAG, new ChapParser());
    }

    private void registerServices() {
        registerParsers();
        registerNetworks();
        registerDataSources();
        registerBusinesses();
    }
}