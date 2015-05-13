package com.dungnh8.truyen_ngon_tinh.business;

import android.util.Log;

import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetBooksFromServerListener;
import com.dungnh8.truyen_ngon_tinh.network.BookNetwork;

import java.util.List;

public class BookBusiness {
    public static final String TAG = BookBusiness.class.getSimpleName();

    private BookNetwork bookNetwork;

    public BookBusiness() {
        bookNetwork = (BookNetwork) ServiceRegistry.getService(BookNetwork.TAG);
    }

    public void getBooksFromServer(int bookType, int pageIndex, final OnGetBooksFromServerListener listener) {
        bookNetwork.getBooksFromServer(bookType, pageIndex, new OnGetBooksFromServerListener() {
            @Override
            public void onSuccess(List<Book> result) {
                Log.i(TAG, result.toString());
            }

            @Override
            public void onFailed(Throwable error) {
                listener.onFailed(error);
            }
        });
    }
}