package com.dungnh8.truyen_ngon_tinh.business;

import android.util.Log;

import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.config.BookConfig;
import com.dungnh8.truyen_ngon_tinh.database.datasource.BookTypeDataSource;
import com.dungnh8.truyen_ngon_tinh.database.model.BookType;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetBookTypesListener;
import com.dungnh8.truyen_ngon_tinh.network.BookTypeNetwork;

import java.util.List;

public class BookTypeBusiness {
    public static final String TAG = BookTypeBusiness.class.getSimpleName();

    private BookTypeNetwork network;
    private BookTypeDataSource dataSource;

    public BookTypeBusiness() {
        network = (BookTypeNetwork) ServiceRegistry.getService(BookTypeNetwork.TAG);
        dataSource = (BookTypeDataSource) ServiceRegistry.getService(BookTypeDataSource.TAG);
    }

    public void getBookTypes(final OnGetBookTypesListener listener) {
        network.getBookTypes(new OnGetBookTypesListener() {
            @Override
            public void onSuccess(List<BookType> result) {
                mergeBookTypes(result);
                List<BookType> bookTypeList = dataSource.getAllBookTypes();
                setConfig(bookTypeList);
                listener.onSuccess(bookTypeList);
            }

            @Override
            public void onFailed(Throwable error) {
                Log.e(TAG, "getBookTypes", error);
                List<BookType> bookTypeList = dataSource.getAllBookTypes();
                listener.onSuccess(bookTypeList);
            }
        });
    }

    private void mergeBookTypes(List<BookType> serverBookTypes) {
        List<BookType> localBookTypes = dataSource.getAllBookTypes();
        if (localBookTypes != null && localBookTypes.size() > 0) {
            for (BookType serverBookType : serverBookTypes) {
                boolean isNew = true;
                for (BookType localBookType : localBookTypes) {
                    if (serverBookType.getServerId() == localBookType.getServerId()) {
                        isNew = false;
                        break;
                    }
                }
                if (isNew) {
                    dataSource.save(serverBookType);
                }
            }
        } else {
            for (BookType serverBookType : serverBookTypes) {
                dataSource.save(serverBookType);
            }
        }
    }

    private void setConfig(List<BookType> bookTypeList) {
        if (bookTypeList != null) {
            BookConfig.BOOK_TYPE = new String[bookTypeList.size()];
            BookConfig.BOOK_TYPE_LINK = new String[bookTypeList.size()];
            for (int i = 0; i < bookTypeList.size(); i++) {
                BookConfig.BOOK_TYPE[i] = bookTypeList.get(i).getTitle();
                BookConfig.BOOK_TYPE_LINK[i] = bookTypeList.get(i).getApi();
            }
        }
    }
}