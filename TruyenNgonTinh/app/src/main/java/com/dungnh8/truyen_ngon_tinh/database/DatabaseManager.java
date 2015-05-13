package com.dungnh8.truyen_ngon_tinh.database;

import android.content.Context;

public class DatabaseManager {
    private DatabaseHelper mHelper;
    private static DatabaseManager instance;

    private static final String TAG = DatabaseManager.class.getSimpleName();

    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    private DatabaseManager(Context context) {
        mHelper = new DatabaseHelper(context);
    }

    public DatabaseHelper getHelper() {
        return mHelper;
    }
}