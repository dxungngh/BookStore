package com.dungnh8.truyen_ngon_tinh.widget;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.dungnh8.truyen_ngon_tinh.R;

public class ConfirmDialog extends Dialog {
    private static ConfirmDialog mConfirmDialog;
    private String mMessage, mTitle;
    private TextView mMessageTextView, mTitleTextView;
    private Button mYesButton, mNoButton;
    private View.OnClickListener mYesOnClickListener, mNoOnClickListener;
    private String mYesTitle, mNoTitle;
    private boolean mIsCancelable = true;

    private static final String TAG = ConfirmDialog.class.getSimpleName();

    public static ConfirmDialog getInstance(Activity activity, String title, String message,
                                            View.OnClickListener yesOnClickListener,
                                            View.OnClickListener noOnClickListener,
                                            String yesTitle, String noTitle,
                                            boolean isCancelable) {
        if (mConfirmDialog == null) {
            mConfirmDialog = new ConfirmDialog(activity, title, message,
                yesOnClickListener, noOnClickListener, yesTitle, noTitle, isCancelable);
        }
        return mConfirmDialog;
    }

    private ConfirmDialog(Activity activity, String title, String message,
                          View.OnClickListener yesOnClickListener,
                          View.OnClickListener noOnClickListener, String yesTitle, String noTitle,
                          boolean isCancelable) {
        super(activity);
        mMessage = message;
        mTitle = title;
        mYesOnClickListener = yesOnClickListener;
        mNoOnClickListener = noOnClickListener;
        mYesTitle = yesTitle;
        mNoTitle = noTitle;
        mIsCancelable = isCancelable;
    }

    private void drawComponentView() {
        setCancelable(mIsCancelable);
        mMessageTextView.setText(mMessage);
        mTitleTextView.setText(mTitle);
        if (!TextUtils.isEmpty(mYesTitle)) {
            mYesButton.setText(mYesTitle);
        }
        if (!TextUtils.isEmpty(mNoTitle)) {
            mNoButton.setText(mNoTitle);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_confirm);
        setComponentView();
        drawComponentView();
        setAllListeners();
    }

    @Override
    protected void onStop() {
        mConfirmDialog = null;
    }

    private void setAllListeners() {
        setNoListener();
        setYesListener();
    }

    private void setNoListener() {
        if (mNoOnClickListener == null) {
            mNoOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mConfirmDialog = null;
                    dismiss();
                }
            };
        }
        mNoButton.setOnClickListener(mNoOnClickListener);
    }

    private void setYesListener() {
        mYesButton.setOnClickListener(mYesOnClickListener);
    }

    private void setComponentView() {
        mTitleTextView = (TextView) findViewById(R.id.dialog_confirm_title);
        mMessageTextView = (TextView) findViewById(R.id.dialog_confirm_message);
        mNoButton = (Button) findViewById(R.id.dialog_confirm_no);
        mYesButton = (Button) findViewById(R.id.dialog_confirm_yes);
    }
}
