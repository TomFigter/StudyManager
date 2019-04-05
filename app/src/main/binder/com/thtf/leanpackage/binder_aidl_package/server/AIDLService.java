package com.thtf.leanpackage.binder_aidl_package.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.thtf.leancommonpackage.BookInterface;
import com.thtf.leancommonpackage.BookRemoteListener;
import com.thtf.leanpackage.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiShiChuang on 2018/11/30.
 */
public class AIDLService extends Service {
    public static final int AIDL_ACTION_ADD = 0x10001;  //向链表中添加数据
    public static final int AIDL_ACTION_REMOVE = 0x10002; //删除链表中数据
    public static final int AIDL_ACTION_GET = 0x10003;  //获取链表中的数据
    private List<Book> bookList = new ArrayList<>();
    private RemoteCallbackList<BookRemoteListener> bookRemoteListenerList = new RemoteCallbackList<>(); //事件监听

    Binder binder = new BookInterface.Stub() {
        @Override
        public void action(int action, int position, Book book) throws RemoteException {
            switch (action) {
                case AIDL_ACTION_ADD:
                    onAddDataToList(book);
                    break;
                case AIDL_ACTION_GET:
                    onGetDataFromList(position);
                    break;
                case AIDL_ACTION_REMOVE:
                    onRemoveDataFormList(book);
                    break;
            }
        }

        @Override
        public void registerListener(BookRemoteListener listener) throws RemoteException {
            if (listener != null)
                bookRemoteListenerList.register(listener);
        }

        @Override
        public void unregisterListener(BookRemoteListener listener) throws RemoteException {
            if (listener != null)
                bookRemoteListenerList.unregister(listener);
        }

        @Override
        public Message getCurrentBookInfo() throws RemoteException {
            Message message = Message.obtain();
            message.obj = bookList.toString();
            return message;
        }
    };

    private void onAddDataToList(Book book) {
        synchronized (this) {
            if (bookList == null)
                bookList = new ArrayList<>();
            bookList.add(book);
//            sendSuccessManager(AIDL_ACTION_ADD, "添加的数据->" + bookList.toString());
            sendSuccessManager(AIDL_ACTION_ADD, book);
        }
    }

    private void onGetDataFromList(int position) {
        synchronized (this) {
            if (bookList != null && bookList.size() > position)
//                sendSuccessManager(AIDL_ACTION_GET, "获取的数据-> " + bookList.get(position).toString());
                sendSuccessManager(AIDL_ACTION_GET, bookList.get(position));
            else
                sendErrorManager(AIDL_ACTION_GET, "链表未声明,添加失败!");
        }
    }

    private void onRemoveDataFormList(Book book) {
        synchronized (this) {
            if (bookList != null) {
                if (bookList.contains(book)) {
                    bookList.remove(book);
                    sendSuccessManager(AIDL_ACTION_REMOVE, book);
                } else
                    sendErrorManager(AIDL_ACTION_REMOVE, "链表中不包含该数据");
            } else
                sendErrorManager(AIDL_ACTION_REMOVE, "链表为空,无法删除数据");
        }
    }

    private void sendErrorManager(int AIDL_ACTION, String ERROR_INFO) {
        Message message = Message.obtain();
        message.what = AIDL_ACTION;
        message.obj = ERROR_INFO;
        customSendCommonManager(message);
    }

    private void sendSuccessManager(int AIDL_ACTION, Book INFO_DATA) {
        Message message = Message.obtain();
        message.what = AIDL_ACTION;
        message.obj = INFO_DATA;
        
        customSendCommonManager(message);
    }

    private void customSendCommonManager(Message message) {
        try {
            final int count = bookRemoteListenerList.beginBroadcast();
            for (int index = 0; index < count; index++) {
                BookRemoteListener bookRemoteListener = bookRemoteListenerList.getBroadcastItem(index);
                if (bookRemoteListener != null) {
                    bookRemoteListener.remoteAction(message.what, message);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            try {
                bookRemoteListenerList.finishBroadcast();
            } catch (IllegalArgumentException illegalArgumentException) {
                Log.i("Error", " while diffusing message to listener finishBroadcast--> " + illegalArgumentException + "");
            }
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}
