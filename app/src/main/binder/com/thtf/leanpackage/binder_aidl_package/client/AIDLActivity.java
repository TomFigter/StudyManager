package com.thtf.leanpackage.binder_aidl_package.client;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thtf.leancommonpackage.BookInterface;
import com.thtf.leancommonpackage.BookRemoteListener;
import com.thtf.leanpackage.Book;
import com.thtf.leanpackage.R;
import com.thtf.leanpackage.binder_aidl_package.server.AIDLService;

/**
 * Created by LiShiChuang on 2018/11/30.
 */
public class AIDLActivity extends Activity implements View.OnClickListener {
    private Button start_aidl_btn;
    private TextView result_aidl_txt;

    private boolean isConnection;
    private BookInterface bookInterface;
    private Button add_data_btn;
    private Button get_data_btn;
    private Button remove_data_btn;
    private Book book;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_aidl);
        start_aidl_btn = (Button) findViewById(R.id.start_aidl_btn);
        add_data_btn = (Button) findViewById(R.id.add_data_btn);
        get_data_btn = (Button) findViewById(R.id.get_data_btn);
        remove_data_btn = (Button) findViewById(R.id.remove_data_btn);
        result_aidl_txt = (TextView) findViewById(R.id.result_aidl_txt);
        remove_data_btn.setOnClickListener(this);
        start_aidl_btn.setOnClickListener(this);
        add_data_btn.setOnClickListener(this);
        get_data_btn.setOnClickListener(this);
        initBookData();
    }


    /**
     * 开启服务
     */
    private void attemptToAIDLBind() {
        Intent intent = new Intent(AIDLActivity.this, AIDLService.class);
        intent.setAction("com.thtf.leanpackage.aidl.server");
        bindService(intent, aidlServiceConnection, Service.BIND_AUTO_CREATE);
    }

    BookRemoteListener bookRemoteListener = new BookRemoteListener.Stub() {
        @Override
        public void remoteAction(int action, Message msg) throws RemoteException {
            handler.sendMessage(msg);
        }
    };
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AIDLService.AIDL_ACTION_ADD:
                    result_aidl_txt.setText(((Book) msg.obj).toString());
                    break;
                case AIDLService.AIDL_ACTION_GET:
                    result_aidl_txt.setText(((Book) msg.obj).toString());
                    break;
                case AIDLService.AIDL_ACTION_REMOVE:
                    result_aidl_txt.setText(((Book) msg.obj).toString());
                    break;
            }
        }
    };


    private void binderAIDLTask() {
        if (!isConnection) {
            attemptToAIDLBind();
            return;
        }
        if (bookInterface == null)
            return;
        try {
            bookInterface.registerListener(bookRemoteListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private ServiceConnection aidlServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnection = true;
            bookInterface = BookInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnection = false;
            attemptToAIDLBind();
        }
    };

    private void initBookData() {
        book = new Book();
        book.setName("李士闯");
        book.setPrice(454);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_aidl_btn:
                binderAIDLTask();
                break;
            case R.id.add_data_btn:
                try {
                    bookInterface.action(AIDLService.AIDL_ACTION_ADD, 0, book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.get_data_btn:
                try {
                    bookInterface.action(AIDLService.AIDL_ACTION_GET, 0, null);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.remove_data_btn:
                try {
                    bookInterface.action(AIDLService.AIDL_ACTION_REMOVE, 0, book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isConnection) {
            attemptToAIDLBind();
        }
    }
}
