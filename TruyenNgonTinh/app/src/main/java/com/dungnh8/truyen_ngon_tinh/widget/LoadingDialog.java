package com.dungnh8.truyen_ngon_tinh.widget;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.utils.PopupUtil;

public class LoadingDialog extends Dialog {
    private static LoadingDialog mLoadingDialog;
    private ImageView mLoadingImageView;

    private static final String TAG = LoadingDialog.class.getSimpleName();

    public static LoadingDialog getInstance(Activity activity) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(activity);
        }
        return mLoadingDialog;
    }

    public LoadingDialog(Activity activity) {
        super(activity);
    }

    private void drawComponentView() {
        setCancelable(false);
        mLoadingImageView.setVisibility(View.VISIBLE);
        PopupUtil.showLoading(mLoadingImageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_loading);
        setComponentView();
        drawComponentView();
    }

    @Override
    protected void onStop() {
        mLoadingDialog = null;
    }

    private void setComponentView() {
        mLoadingImageView = (ImageView) findViewById(R.id.loading);
    }
}