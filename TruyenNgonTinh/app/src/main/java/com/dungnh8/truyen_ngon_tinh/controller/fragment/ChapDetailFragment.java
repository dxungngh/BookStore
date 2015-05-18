package com.dungnh8.truyen_ngon_tinh.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.business.ChapBusiness;
import com.dungnh8.truyen_ngon_tinh.database.model.Chap;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetChapDetailListener;

public class ChapDetailFragment extends Fragment {
    private static final String TAG = ChapDetailFragment.class.getSimpleName();

    private WebView contentWebView;

    private Chap chap;

    private ChapBusiness chapBusiness;

    public static ChapDetailFragment newInstance(Chap chap) {
        ChapDetailFragment fragment = new ChapDetailFragment();
        fragment.chap = chap;
        return fragment;
    }

    public ChapDetailFragment() {
        initData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chapt_detail, container, false);
        setComponentViews(view);
        setAllListener();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getChapDetail();
    }

    private void drawChap(Chap chap) {
        contentWebView.loadData(chap.getContent(), "text/html; charset=utf-8", "UTF-8");
    }

    private void getChapDetail() {
        chapBusiness.getChapDetail(chap, new OnGetChapDetailListener() {
            @Override
            public void onSuccess(Chap result) {
                chap = result;
                drawChap(chap);
            }

            @Override
            public void onFailed(Throwable error) {
                Log.e(TAG, "getChapDetail", error);
            }
        });
    }

    private void initData() {
        chapBusiness = (ChapBusiness) ServiceRegistry.getService(ChapBusiness.TAG);
    }

    private void setAllListener() {
    }

    private void setComponentViews(View rootView) {
        contentWebView = (WebView) rootView.findViewById(R.id.fragment_chap_detail_content);
    }
}