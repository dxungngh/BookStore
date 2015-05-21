package com.dungnh8.truyen_ngon_tinh.business;

import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.database.datasource.BookDataSource;
import com.dungnh8.truyen_ngon_tinh.database.model.Book;
import com.dungnh8.truyen_ngon_tinh.database.model.Chap;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetBookDetailListener;
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

    public void getBookDetail(String api, int index, final OnGetBookDetailListener listener) {
        network.getBookDetail(api, index, new OnGetBookDetailListener() {
            @Override
            public void onSuccess(List<Chap> chaps) {
                listener.onSuccess(chaps);
            }

            @Override
            public void onFailed(Throwable error) {
                listener.onFailed(error);
            }
        });
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

    public Book getBookFromDatabase(long serverBookId) {
        return dataSource.getBookByServerId(serverBookId);
    }

    public List<Book> getHistoryBooks() {
        return dataSource.getHistoryBooks();
    }

    public void getHotBooks(int pageIndex, final OnGetBooksFromServerListener listener) {
        network.getHotBooks(pageIndex, 10, new OnGetBooksFromServerListener() {
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

    public void saveCurrentChap(Book book, long currentChapServerId) {
        Book localBook = getBookFromDatabase(book.getServerId());
        if (localBook != null) {
            localBook.setCurrentChap(currentChapServerId);
            localBook.setIsRead(true);
            dataSource.updateBook(localBook);
        } else {
            book.setCurrentChap(currentChapServerId);
            book.setIsRead(true);
            dataSource.createBook(book);
        }
    }
}