package com.dungnh8.truyen_ngon_tinh.business;

import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.database.datasource.ChapDataSource;
import com.dungnh8.truyen_ngon_tinh.database.model.Chap;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetChapDetailListener;
import com.dungnh8.truyen_ngon_tinh.network.ChapNetwork;

public class ChapBusiness {
    public static final String TAG = ChapBusiness.class.getSimpleName();

    private ChapNetwork network;
    private ChapDataSource dataSource;

    public ChapBusiness() {
        network = (ChapNetwork) ServiceRegistry.getService(ChapNetwork.TAG);
        dataSource = (ChapDataSource) ServiceRegistry.getService(ChapDataSource.TAG);
    }

    private Chap getChapFromDatabase(long serverChapId) {
        return dataSource.getChapByServerId(serverChapId);
    }

    public void getChapDetail(Chap chap, final OnGetChapDetailListener listener) {
        Chap localChap = getChapFromDatabase(chap.getServerId());
        if (localChap != null) {
            listener.onSuccess(localChap);
        } else {
            network.getChapDetail(chap.getApi(), new OnGetChapDetailListener() {
                @Override
                public void onSuccess(Chap result) {
                    dataSource.createChap(result);
                    listener.onSuccess(result);
                }

                @Override
                public void onFailed(Throwable error) {
                    listener.onFailed(error);
                }
            });
        }
    }
}