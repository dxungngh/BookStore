package com.dungnh8.tieu_thuyet_ngon_tinh.business;

import com.dungnh8.tieu_thuyet_ngon_tinh.ServiceRegistry;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.datasource.ChapDataSource;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.Chap;
import com.dungnh8.tieu_thuyet_ngon_tinh.listener.OnGetChapDetailListener;
import com.dungnh8.tieu_thuyet_ngon_tinh.network.ChapNetwork;

public class ChapBusiness {
    public static final String TAG = ChapBusiness.class.getSimpleName();

    private ChapNetwork network;
    private ChapDataSource dataSource;

    public ChapBusiness() {
        network = (ChapNetwork) ServiceRegistry.getService(ChapNetwork.TAG);
        dataSource = (ChapDataSource) ServiceRegistry.getService(ChapDataSource.TAG);
    }

    private Chap createChap(Chap chap) {
        Chap localChap = dataSource.getChapByServerId(chap.getServerId());
        if (localChap != null) {
            return localChap;
        }
        dataSource.createChap(chap);
        return chap;
    }

    public Chap getChapFromDatabase(long serverChapId) {
        return dataSource.getChapByServerId(serverChapId);
    }

    public void getChapDetail(long serverId, String api, final OnGetChapDetailListener listener) {
        Chap localChap = getChapFromDatabase(serverId);
        if (localChap != null) {
            listener.onSuccess(localChap);
        } else {
            network.getChapDetail(api, new OnGetChapDetailListener() {
                @Override
                public void onSuccess(Chap result) {
                    Chap chap = createChap(result);
                    listener.onSuccess(chap);
                }

                @Override
                public void onFailed(Throwable error) {
                    listener.onFailed(error);
                }
            });
        }
    }
}