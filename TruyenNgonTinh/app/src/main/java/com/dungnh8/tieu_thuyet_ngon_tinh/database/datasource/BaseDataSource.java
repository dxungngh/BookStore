package com.dungnh8.tieu_thuyet_ngon_tinh.database.datasource;

public class BaseDataSource {
    private static final String TAG = BaseDataSource.class.getSimpleName();

    protected boolean getBooleanFromAffectedRows(int affectedRows) {
        if (affectedRows > 0) {
            return true;
        }
        return false;
    }
}
