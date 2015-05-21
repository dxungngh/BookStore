package com.dungnh8.truyen_ngon_tinh.controller.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.dungnh8.truyen_ngon_tinh.R;
import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.business.BookBusiness;
import com.dungnh8.truyen_ngon_tinh.business.ChapBusiness;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.database.model.Chap;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetChapDetailListener;
import com.dungnh8.truyen_ngon_tinh.task.CrawNextChapsTask;

public class ChapDetailFragment extends Fragment {
    private static final String TAG = ChapDetailFragment.class.getSimpleName();

    private Handler handler;
    private WebView contentWebView;

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
        setComponentViews(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getChapDetail(chap.getServerId(), chap.getApi());
    }

    private void drawChap(final Chap chap) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                contentWebView.loadData(chap.getContent(), "text/html; charset=utf-8", "UTF-8");
            }
        });
        Log.i(TAG, "current chap Id: " + chap.getServerId());
        bookBusiness.saveCurrentChap(book, chap.getServerId());
        task = new CrawNextChapsTask(chap);
        task.run();
    }

    private void getChapDetail(long serverId, String api) {
        chapBusiness.getChapDetail(serverId, api, new OnGetChapDetailListener() {
            @Override
            public void onSuccess(Chap result) {
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

    private void setComponentViews(View rootView) {
        contentWebView = (WebView) rootView.findViewById(R.id.fragment_chap_detail_content);
    }
}