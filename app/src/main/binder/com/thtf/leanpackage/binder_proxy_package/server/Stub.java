package com.thtf.leanpackage.binder_proxy_package.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.thtf.leanpackage.Book;
import com.thtf.leanpackage.binder_proxy_package.proxy.Proxy;

import java.util.List;

/**
 * Created by LiShiChuang on 2018/11/29.
 */
public abstract class Stub extends Binder implements BookManager {
    //维护表单(类)的具体包名类名标记
    private static final String DESCRIPTOR = "com.thtf.leanpackage.binder_proxy_package.server.BookManager"; 
    public static final int TRANSAVTION_getBooks = IBinder.FIRST_CALL_TRANSACTION;
    public static final int TRANSAVTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public Stub() {
        this.attachInterface(this, DESCRIPTOR);
    }
    
    public static BookManager asInterface(IBinder binder) {
        if (binder == null)
            return null;
        IInterface iInterface = binder.queryLocalInterface(DESCRIPTOR); //查询自己维护的表单(类)
        if (iInterface != null && iInterface instanceof BookManager)//若iInterface继承于BookManager
            return (BookManager) iInterface; //返回BookManager的代理对象iInterface
        return new Proxy(binder);
    }

    @Override
    public IBinder asBinder() {return this;}
    

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
            case TRANSAVTION_getBooks:
                data.enforceInterface(DESCRIPTOR);
                List<Book> result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;
            case TRANSAVTION_addBook:
                data.enforceInterface(DESCRIPTOR);
                Book book = null;
                if (data.readInt() != 0)
                    book = Book.CREATOR.createFromParcel(data);
                this.addBook(book);
                reply.writeNoException();
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }
}
