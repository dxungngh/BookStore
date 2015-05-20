package com.dungnh8.truyen_ngon_tinh.database.datasource;

import android.content.Context;
import android.util.Log;

import com.dungnh8.truyen_ngon_tinh.database.DatabaseHelper;
import com.dungnh8.truyen_ngon_tinh.database.DatabaseManager;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.j256.ormlite.stmt.PreparedQuery;

import java.util.List;

public class BookDataSource extends BaseDataSource {
    public static final String TAG = BookDataSource.class.getSimpleName();

    private Context context;

    public BookDataSource(Context context) {
        this.context = context;
    }

    public void createBook(Book book) {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(context).getHelper();
            book.setUpdatedAt(System.currentTimeMillis());
            helper.getBookDao().create(book);
        } catch (Exception e) {
            Log.e(TAG, "createBook", e);
        }
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

    public List<Book> getHistoryBooks() {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(context).getHelper();
            PreparedQuery<Book> preparedQuery = helper.getBookDao().queryBuilder()
                .orderBy(Book.Fields.UPDATED_AT, false)
                .where().eq(Book.Fields.IS_READ, true)
                .prepare();
            List<Book> books = helper.getBookDao().query(preparedQuery);
            return books;
        } catch (Exception e) {
            Log.e(TAG, "getHistoryBooks", e);
            return null;
        }
    }

    public void updateBook(Book book) {
        try {
            DatabaseHelper helper = DatabaseManager.getInstance(context).getHelper();
            book.setUpdatedAt(System.currentTimeMillis());
            helper.getBookDao().update(book);
        } catch (Exception e) {
            Log.e(TAG, "updateBook", e);
        }
    }
}
