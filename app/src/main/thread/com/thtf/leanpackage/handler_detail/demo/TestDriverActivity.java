package com.thtf.leanpackage.handler_detail.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.thtf.leanpackage.R;

public class TestDriverActivity extends Activity {
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        textview = (TextView) findViewById(R.id.textview);
        // 创建并启动工作线程
        Thread workerThread = new Thread(new SampleTask(new MyHandler()));
        workerThread.start();
    }

    public void appendText(String msg) {
        textview.setText(textview.getText() + "\n" + msg);
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String result = msg.getData().getString("message");
            // 更新UI
            appendText(result);
        }
    }
}
