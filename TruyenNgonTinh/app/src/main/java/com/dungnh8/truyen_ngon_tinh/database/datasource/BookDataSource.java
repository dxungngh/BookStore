package com.dungnh8.truyen_ngon_tinh.database.datasource;

import android.content.Context;

public class BookDataSource extends BaseDataSource {
    public static final String TAG = BookDataSource.class.getSimpleName();

    private Context context;

    public BookDataSource(Context context) {
        this.context = context;
    }
}
