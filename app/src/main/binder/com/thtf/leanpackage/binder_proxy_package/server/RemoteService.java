package com.thtf.leanpackage.binder_proxy_package.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.thtf.leanpackage.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiShiChuang on 2018/11/29.
 */
public class RemoteService extends Service {
    private List<Book> bookList = new ArrayList<>();

    public RemoteService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Book book = new Book();
        book.setName("三体");
        book.setPrice(88);
        bookList.add(book);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bookManager;
    }

    private final Stub bookManager = new Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            synchronized (this) {
                if (bookList != null)
                    return bookList;
            }
            return new ArrayList<>();
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (this) {
                if (bookList == null) {
                    bookList = new ArrayList<>();
                }
                if (book == null)
                    return;
                book.setPrice(book.getPrice() * 2);
                bookList.add(book);
                Log.i("Server", "bookList: " + book.toString());
            }
        }
    };
}
