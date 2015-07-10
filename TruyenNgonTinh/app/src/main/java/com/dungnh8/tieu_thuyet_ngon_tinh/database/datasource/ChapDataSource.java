package com.dungnh8.tieu_thuyet_ngon_tinh.database.datasource;

import android.content.Context;
import android.util.Log;

import com.dungnh8.tieu_thuyet_ngon_tinh.database.DatabaseHelper;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.DatabaseManager;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.Book;
import com.dungnh8.tieu_thuyet_ngon_tinh.database.model.Chap;
import com.j256.ormlite.stmt.PreparedQuery;

public class ChapDataSource extends BaseDataSource {
    public static final String TAG = ChapDataSource.class.getSimpleName();

    private Context context;

    public ChapDataSource(Context context) {
        this.context = context;
    }

    public void createChap(Chap chap) {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(context).getHelper();
            helper.getChapDao().create(chap);
        } catch (Exception e) {
            Log.e(TAG, "createChap", e);
        }
    }

    public Chap getChapByServerId(long serverId) {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(context).getHelper();
            PreparedQuery<Chap> preparedQuery = helper.getChapDao().queryBuilder().where().eq(
                Book.Fields.SERVER_ID, serverId).prepare();
            Chap chap = helper.getChapDao().queryForFirst(preparedQuery);
            return chap;
        } catch (Exception e) {
            Log.e(TAG, "getChapByServerId", e);
            return null;
        }
    }
}