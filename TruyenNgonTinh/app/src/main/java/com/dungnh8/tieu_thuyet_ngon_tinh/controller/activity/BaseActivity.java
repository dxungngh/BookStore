package com.dungnh8.tieu_thuyet_ngon_tinh.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.dungnh8.tieu_thuyet_ngon_tinh.widget.ConfirmDialog;
import com.dungnh8.tieu_thuyet_ngon_tinh.widget.ErrorDialog;
import com.dungnh8.tieu_thuyet_ngon_tinh.widget.LoadingDialog;
import com.startapp.android.publish.StartAppAd;

public class BaseActivity extends ActionBarActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    protected StartAppAd startAppAd = new StartAppAd(this);

    private Handler handler;
    private ConfirmDialog confirmDialog;
    private ErrorDialog errorDialog;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startAppAd.showAd();
        startAppAd.loadAd();
        handler = new Handler();
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
        hideLoadingDialog();
    }

    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }

    protected void hideConfirmPopup() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (confirmDialog != null && confirmDialog.isShowing()) {
                        confirmDialog.dismiss();
                    }
                    confirmDialog = null;
                } catch (Exception e) {
                    Log.e(TAG, "hideConfirmPopup", e);
                }
            }
        });
    }

    public void hideLoadingDialog() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (loadingDialog != null) {
                        loadingDialog.dismiss();
                    }
                    loadingDialog = null;
                } catch (Exception e) {
                    Log.e(TAG, "hideLoadingDialog", e);
                }
            }
        });
    }

    public void showConfirmPopup(final String title, final String message,
                                 final View.OnClickListener yesOnClickListener) {
        showCommonConfirmDialog(title, message, yesOnClickListener, null, null, null, true);
    }

    public void showConfirmPopup(final String title, final String message,
                                 final View.OnClickListener yesOnClickListener,
                                 final View.OnClickListener noOnClickListener,
                                 final boolean isCancelable) {
        showCommonConfirmDialog(
            title, message, yesOnClickListener, noOnClickListener, null, null, isCancelable);
    }

    public void showConfirmPopup(final String title, final String message,
                                 final View.OnClickListener yesOnClickListener,
                                 final String yesTitle, final String noTitle) {
        showCommonConfirmDialog(
            title, message, yesOnClickListener, null, yesTitle, noTitle, true);
    }

    private void showCommonConfirmDialog(final String title, final String message,
                                         final View.OnClickListener yesOnClickListener,
                                         final View.OnClickListener noOnClickListener,
                                         final String yesTitle, final String noTitle,
                                         final boolean isCancelable) {
        hideConfirmPopup();
        if (confirmDialog == null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    confirmDialog = ConfirmDialog.getInstance(BaseActivity.this, title, message,
                        yesOnClickListener, noOnClickListener, yesTitle, noTitle, isCancelable);
                    if (confirmDialog != null) {
                        confirmDialog.show();
                    }
                }
            });
        }
    }

    public void showLoadingDialog(final Activity activity) {
        hideLoadingDialog();
        if (loadingDialog == null && !isFinishing() && !activity.isFinishing()) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        loadingDialog = LoadingDialog.getInstance(activity);
                        if (loadingDialog != null && !loadingDialog.isShowing()
                            && !activity.isFinishing() && !isFinishing()) {
                            loadingDialog.show();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "showLoadingDialog", e);
                        hideLoadingDialog();
                    }
                }
            });
        }
    }
}