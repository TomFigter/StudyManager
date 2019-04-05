package com.thtf.leanpackage.binder_proxy_package.proxy;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.thtf.leanpackage.Book;
import com.thtf.leanpackage.binder_proxy_package.server.BookManager;
import com.thtf.leanpackage.binder_proxy_package.server.Stub;

import java.util.List;

/**
 * Created by LiShiChuang on 2018/11/29.
 */
public class Proxy implements BookManager {
    private IBinder remote;
    private static final String DESCRIPTOR = "com.thtf.leanpackage.binder_proxy_package.server.BookManager";

    public Proxy(IBinder remote) {
        this.remote = remote;
    }
    
    @Override
    public List<Book> getBookList() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();
        List<Book> result;
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            remote.transact(Stub.TRANSAVTION_getBooks, data, replay, 0);
            replay.readException();
            result = replay.createTypedArrayList(Book.CREATOR);
        } finally {
            replay.recycle();
            data.recycle();
        }
        return result;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            if (book != null) {
                data.writeInt(1);
                book.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            remote.transact(Stub.TRANSAVTION_addBook, data, replay, 0);
            replay.readException();
        } finally {
            replay.recycle();
            data.recycle();
        }
    }

    @Override
    public IBinder asBinder() {
        return remote;
    }
}
