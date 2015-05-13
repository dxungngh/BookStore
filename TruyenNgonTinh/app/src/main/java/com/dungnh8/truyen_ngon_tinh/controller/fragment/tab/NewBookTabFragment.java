package com.dungnh8.truyen_ngon_tinh.controller.fragment.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.config.ExtrasConfig;

public class NewBookTabFragment extends Fragment {
    private static final String TAG = NewBookTabFragment.class.getSimpleName();

    private int selectedBookType = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_book_list, container, false);
        initData();
        setComponentViews(rootView);
        return rootView;
    }

    private void initData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            selectedBookType = bundle.getInt(ExtrasConfig.SELECTED_BOOK_TYPE, 0);
        }
        Log.i(TAG, "selectedBookType: " + selectedBookType);
    }

    private void setComponentViews(View rootView) {

    }
}