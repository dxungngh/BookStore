package com.dungnh8.truyen_ngon_tinh.controller.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.startapp.android.publish.StartAppAd;

public class BaseActivity extends ActionBarActivity {
    protected StartAppAd startAppAd = new StartAppAd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startAppAd.showAd();
        startAppAd.loadAd();
    }

    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        startAppAd.onPause();
    }

    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }
}