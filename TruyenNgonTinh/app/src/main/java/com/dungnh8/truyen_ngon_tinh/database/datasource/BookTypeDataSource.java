package com.dungnh8.truyen_ngon_tinh.database.datasource;

import android.content.Context;
import android.util.Log;

import com.dungnh8.truyen_ngon_tinh.database.DatabaseManager;
import com.dungnh8.truyen_ngon_tinh.database.model.BookType;

import java.util.List;

public class BookTypeDataSource extends BaseDataSource {
    public static final String TAG = BookTypeDataSource.class.getSimpleName();

    private Context context;

    public BookTypeDataSource(Context context) {
        this.context = context;
    }

    public List<BookType> getAllBookTypes() {
        try {
            List<BookType> bookTypeList =
                DatabaseManager.getInstance(context).getHelper().getBookTypeDao().queryForAll();
            return bookTypeList;
        } catch (Exception e) {
            Log.e(TAG, "getAllBookTypes", e);
            return null;
        }
    }

    public void save(BookType bookType) {
        try {
            DatabaseManager.getInstance(context).getHelper().getBookTypeDao().create(bookType);
        } catch (Exception e) {
            Log.e(TAG, "save", e);
        }
    }
}