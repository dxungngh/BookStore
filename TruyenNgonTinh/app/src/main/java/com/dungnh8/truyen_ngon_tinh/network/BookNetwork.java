package com.dungnh8.truyen_ngon_tinh.network;

import com.dungnh8.truyen_ngon_tinh.ServiceRegistry;
import com.dungnh8.truyen_ngon_tinh.listener.OnGetBooksFromServerListener;
import com.dungnh8.truyen_ngon_tinh.parser.BookParser;

public class BookNetwork extends BaseNetwork {
    public static final String TAG = BookNetwork.class.getSimpleName();

    private MyVolley volley;

    private BookParser bookParser;

    public BookNetwork() {
        volley = (MyVolley) ServiceRegistry.getService(MyVolley.TAG);
        bookParser = (BookParser) ServiceRegistry.getService(BookParser.TAG);
    }

    public void getBooksFromServer(int bookType, int pageIndex, final OnGetBooksFromServerListener listener) {
    }
}