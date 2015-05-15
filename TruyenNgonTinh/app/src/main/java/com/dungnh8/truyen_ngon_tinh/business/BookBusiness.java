package com.dungnh8.truyen_ngon_tinh.business;

import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.database.datasource.BookDataSource;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetBooksFromServerListener;
import com.dungnh8.truyen_ngon_tinh.network.BookNetwork;

import java.util.List;

public class BookBusiness {
    public static final String TAG = BookBusiness.class.getSimpleName();

    private BookNetwork network;
    private BookDataSource dataSource;

    public BookBusiness() {
        network = (BookNetwork) ServiceRegistry.getService(BookNetwork.TAG);
        dataSource = (BookDataSource) ServiceRegistry.getService(BookDataSource.TAG);
    }

    public void getBooksFromServer(boolean isSearching, String keyword, int bookType, int pageIndex, final OnGetBooksFromServerListener listener) {
        network.getBooksFromServer(isSearching, keyword, bookType, pageIndex, 10,
            new OnGetBooksFromServerListener() {
                @Override
                public void onSuccess(List<Book> result) {
                    listener.onSuccess(result);
                }

                @Override
                public void onFailed(Throwable error) {
                    listener.onFailed(error);
                }
            });
    }
}