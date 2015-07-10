package com.dungnh8.tieu_thuyet_ngon_tinh.task;

import android.util.Log;

import com.dungnh8.tieu_thuyet_ngon_tinh.ServiceRegistry;
import com.dungnh8.tieu_thuyet_ngon_tinh.business.ChapBusiness;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.Chap;
import com.dungnh8.tieu_thuyet_ngon_tinh.listener.OnGetChapDetailListener;

public class CrawNextChapsTask implements Runnable {
    private static final String TAG = CrawNextChapsTask.class.getSimpleName();
    private static final int MAX_CRAWLED_CHAPS = 3;

    private Chap currentChap;
    private boolean isContinue = true;
    private int totalCrawledChaps = 0;

    private ChapBusiness chapBusiness;

    public CrawNextChapsTask(Chap currentChap) {
        this.currentChap = currentChap;
        chapBusiness = (ChapBusiness) ServiceRegistry.getService(ChapBusiness.TAG);
    }

    @Override
    public void run() {
        crawNextChap();
    }

    private void crawNextChap() {
        long serverChapId = currentChap.getNextChap();
        String serverChapApi = currentChap.getNextApi();

        if (serverChapId <= 0 || totalCrawledChaps >= MAX_CRAWLED_CHAPS || !isContinue) {
            return;
        }

        Chap localChap = chapBusiness.getChapFromDatabase(serverChapId);
        if (localChap != null) {
            currentChap = localChap;
            crawNextChap();
        } else {
            chapBusiness.getChapDetail(serverChapId, serverChapApi, new OnGetChapDetailListener() {
                @Override
                public void onSuccess(Chap result) {
                    totalCrawledChaps++;
                    currentChap = result;
                    isContinue = true;
                    crawNextChap();
                }

                @Override
                public void onFailed(Throwable error) {
                    isContinue = false;
                    Log.i(TAG, "crawNextChap", error);
                }
            });
        }
    }
}