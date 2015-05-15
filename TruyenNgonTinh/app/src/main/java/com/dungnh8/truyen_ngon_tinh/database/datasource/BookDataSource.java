package com.dungnh8.truyen_ngon_tinh.database.datasource;

import android.content.Context;
import android.util.Log;

import com.dungnh8.truyen_ngon_tinh.database.DatabaseHelper;
import com.dungnh8.truyen_ngon_tinh.database.DatabaseManager;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.j256.ormlite.stmt.PreparedQuery;

public class BookDataSource extends BaseDataSource {
    public static final String TAG = BookDataSource.class.getSimpleName();

    private Context context;

    public BookDataSource(Context context) {
        this.context = context;
    }

    public Book getBookByServerId(long serverId) {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(context).getHelper();
            PreparedQuery<Book> preparedQuery = helper.getBookDao().queryBuilder().where().eq(
                Book.Fields.SERVER_ID, serverId).prepare();
            Book book = helper.getBookDao().queryForFirst(preparedQuery);
            return book;
        } catch (Exception e) {
            Log.e(TAG, "getBookByServerId", e);
            return null;
        }
    }
}
