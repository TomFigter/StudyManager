package com.thtf.leanpackage.binder_proxy_package.server;

import android.os.IInterface;
import android.os.RemoteException;

import com.thtf.leanpackage.Book;

import java.util.List;

/**
 * Created by LiShiChuang on 2018/11/29.
 */
public interface BookManager extends IInterface {
    List<Book> getBookList() throws RemoteException;

    void addBook(Book book) throws RemoteException;
}
