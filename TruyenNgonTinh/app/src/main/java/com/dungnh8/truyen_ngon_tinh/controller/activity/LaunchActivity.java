package com.dungnh8.truyen_ngon_tinh.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.business.BookTypeBusiness;
import com.dungnh8.truyen_ngon_tinh.config.AdvertiseConfig;
import com.dungnh8.truyen_ngon_tinh.database.model.BookType;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetBookTypesListener;
import com.startapp.android.publish.StartAppSDK;

import java.util.List;

public class LaunchActivity extends Activity {
    private static final String TAG = LaunchActivity.class.getSimpleName();

    private BookTypeBusiness BookTypeBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        initData();
        getBookTypes();
    }

    private void getBookTypes() {
        BookTypeBusiness.getBookTypes(new OnGetBookTypesListener() {
            @Override
            public void onSuccess(List<BookType> result) {
                goToMainScreen();
            }

            @Override
            public void onFailed(Throwable error) {
                Log.e(TAG, "getBookTypes", error);
            }
        });
    }

    private void goToMainScreen() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initData() {
        BookTypeBusiness = (BookTypeBusiness) ServiceRegistry.getService(BookTypeBusiness.TAG);
        StartAppSDK.init(this, AdvertiseConfig.ACCOUNT_ID, AdvertiseConfig.APP_ID, true);
    }
}