package com.thtf.leanpackage.binder_proxy_package.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thtf.leanpackage.Book;
import com.thtf.leanpackage.R;
import com.thtf.leanpackage.binder_proxy_package.server.BookManager;
import com.thtf.leanpackage.binder_proxy_package.server.RemoteService;
import com.thtf.leanpackage.binder_proxy_package.server.Stub;

import java.util.List;

/**
 * Created by LiShiChuang on 2018/11/29.
 */
public class ClientActivity extends Activity implements View.OnClickListener {
    private Button start_remote_btn;
    private BookManager bookManager;
    private TextView result_remote_txt;
    private boolean isConnection = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        start_remote_btn = (Button) findViewById(R.id.start_remote_btn);
        result_remote_txt = (TextView) findViewById(R.id.result_remote_txt);
        start_remote_btn.setOnClickListener(this);
    }

    public void onStartOnClick() {
        if (!isConnection) {
            attemptToBindService();
            return;
        }
        if (bookManager == null)
            return;
        try {
            Book book = new Book();
            book.setPrice(202);
            book.setName("编码");
            //调用代理对象的执行方法
            bookManager.addBook(book);
            Log.i("ClientActivity", bookManager.getBookList().toString());
            result_remote_txt.setText(bookManager.getBookList().toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启服务
     */
    private void attemptToBindService() {
        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction("com.thtf.leanpackage.binder_proxy_package.server");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnection = true;
            //获取Binder驱动的代理对象
            bookManager = Stub.asInterface(service); 
            if (bookManager != null) {
                try {
                    //调用代理对象的执行方法
                    List<Book> bookList = bookManager.getBookList(); 
                    Log.i("ClientActivity", bookList.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnection = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if (!isConnection)
            attemptToBindService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isConnection)
            unbindService(serviceConnection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_remote_btn:
                onStartOnClick();
                break;
        }
    }
}
