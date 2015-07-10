package com.dungnh8.tieu_thuyet_ngon_tinh.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyboardUtil {
    private static final String TAG = KeyboardUtil.class.getSimpleName();

    public static void hideSoftKeyboardOfEditText(Context context, EditText editText) {
        try {
            InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception e) {
            Log.e(TAG, "hideSoftKeyboardOfEditText", e);
        }
    }

    public static void hideSoftKeyboardOfView(Context context, ViewGroup view) {
        try {
            int count = view.getChildCount();
            for (int i = 0; i < count; i++) {
                View childView = view.getChildAt(i);
                if (childView instanceof EditText) {
                    hideSoftKeyboardOfEditText(context, (EditText) childView);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "hideSoftKeyboardOfView", e);
        }
    }

    public static void showKeyboardOnEditText(EditText editText, final Window window) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                try {
                    window.setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                } catch (Exception e) {
                    Log.e(TAG, "showKeyboard", e);
                }
            }
        });
    }
}