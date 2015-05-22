package com.dungnh8.truyen_ngon_tinh.utils;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.dungnh8.truyen_ngon_tinh.R;

public class PopupUtil {
    private static final String TAG = PopupUtil.class.getSimpleName();

    public static void dismissLoading(ImageView loadingImageView) {
        final AnimationDrawable loadAnimation = (AnimationDrawable) loadingImageView.getDrawable();
        loadAnimation.stop();
        loadingImageView.setVisibility(View.INVISIBLE);
    }

    public static void showLoading(ImageView loadingImageView) {
        loadingImageView.setVisibility(View.VISIBLE);
        loadingImageView.setImageResource(R.drawable.loading);
        final AnimationDrawable loadAnimation = (AnimationDrawable) loadingImageView.getDrawable();
        loadingImageView.setVisibility(View.VISIBLE);
        loadAnimation.start();
    }
}