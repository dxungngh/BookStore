package com.dungnh8.tieu_thuyet_ngon_tinh.controller.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Toast;

import com.dungnh8.tieu_thuyet_ngon_tinh.R;
import com.dungnh8.tieu_thuyet_ngon_tinh.ServiceRegistry;
import com.dungnh8.tieu_thuyet_ngon_tinh.business.BookBusiness;
import com.dungnh8.tieu_thuyet_ngon_tinh.business.ChapBusiness;
import com.dungnh8.tieu_thuyet_ngon_tinh.controller.activity.BaseActivity;
import com.dungnh8.tieu_thuyet_ngon_tinh.controller.activity.ChapDetailActivity;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.Book;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.Chap;
import com.dungnh8.tieu_thuyet_ngon_tinh.listener.OnGetChapDetailListener;
import com.dungnh8.tieu_thuyet_ngon_tinh.task.CrawNextChapsTask;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

public class ChapDetailFragment extends Fragment implements ObservableScrollViewCallbacks {
    private static final String TAG = ChapDetailFragment.class.getSimpleName();

    private static final int DEFAULT_TEXT_SIZE = 15;

    private Handler handler;
    private ObservableWebView contentWebView;

    private Book book;
    private Chap chap;

    private BookBusiness bookBusiness;
    private ChapBusiness chapBusiness;

    private CrawNextChapsTask task;

    public static ChapDetailFragment newInstance(Book book, Chap chap) {
        ChapDetailFragment fragment = new ChapDetailFragment();
        fragment.book = book;
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
        bindComponentViews(view);
        setAllListeners();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getChapDetail(chap.getServerId(), chap.getApi());
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ChapDetailActivity chapDetailActivity = (ChapDetailActivity) getActivity();
        ActionBar actionBar = chapDetailActivity.getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        if (scrollState == ScrollState.UP) {
            if (actionBar.isShowing()) {
                actionBar.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!actionBar.isShowing()) {
                actionBar.show();
            }
        }
    }

    private void bindComponentViews(View rootView) {
        contentWebView = (ObservableWebView) rootView.findViewById(R.id.fragment_chap_detail_content);
    }

    private void drawChap(final Chap chap) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                int textSize = getTextSize();
                contentWebView.loadData(chap.getContent(), "text/html; charset=utf-8", "UTF-8");
                final WebSettings webSettings = contentWebView.getSettings();
                webSettings.setDefaultFontSize(textSize);
            }
        });
        Log.i(TAG, "current chap Id: " + chap.getServerId());
        bookBusiness.saveCurrentChap(book, chap.getServerId());
        task = new CrawNextChapsTask(chap);
        task.run();
    }

    private void getChapDetail(long serverId, String api) {
        final BaseActivity baseActivity = (BaseActivity) getActivity();
        baseActivity.showLoadingDialog(baseActivity);
        chapBusiness.getChapDetail(serverId, api, new OnGetChapDetailListener() {
            @Override
            public void onSuccess(Chap result) {
                baseActivity.hideLoadingDialog();
                chap = result;
                if (chap != null) {
                    drawChap(chap);
                }
            }

            @Override
            public void onFailed(Throwable error) {
                Log.e(TAG, "getChapDetail", error);
            }
        });
    }

    public void goTo(long chapServerId, String chapApi) {
        getChapDetail(chapServerId, chapApi);
    }

    public void goToNextChap() {
        if (chap.getNextChap() <= 0) {
            Toast.makeText(getActivity(), getString(R.string.error_last_chap), Toast.LENGTH_LONG)
                .show();
        } else {
            goTo(chap.getNextChap(), chap.getNextApi());
        }
    }

    public void goToPrevChap() {
        if (chap.getPrevChap() > 0) {
            goTo(chap.getPrevChap(), chap.getPrevApi());
        }
    }

    private void initData() {
        bookBusiness = (BookBusiness) ServiceRegistry.getService(BookBusiness.TAG);
        chapBusiness = (ChapBusiness) ServiceRegistry.getService(ChapBusiness.TAG);
        handler = new Handler();
    }

    public void modifyTextSize(boolean isIncreasing) {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int textSize = getTextSize();

        SharedPreferences.Editor editor = sharedPref.edit();
        if (isIncreasing) {
            textSize += 2;
        } else {
            textSize -= 2;
        }
        editor.putInt(getString(R.string.tieu_thuyet_ngon_tinh_text_size), textSize);
        editor.commit();

        final WebSettings webSettings = contentWebView.getSettings();
        webSettings.setDefaultFontSize(textSize);
    }

    private int getTextSize() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        int textSize = sharedPref.getInt(getString(R.string.tieu_thuyet_ngon_tinh_text_size), DEFAULT_TEXT_SIZE);
        return textSize;
    }

    private void setAllListeners() {
        contentWebView.setScrollViewCallbacks(this);
    }
}