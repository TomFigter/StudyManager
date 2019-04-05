package com.thtf.leanpackage.thread_pool_mode.thread_pool_mode_demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thtf.leanpackage.R;

/**
 * Created by LiShiChuang on 2018/11/22.
 */
public class ThreadActivity extends Activity implements View.OnClickListener, ThreadContract.View {
    private Button start_thread_btn;
    private Button close_thread_btn;
    private TextView result_txt;
    private TextView result_thread_txt;
    private ThreadTaskPresenter threadTaskPresenter;
    private StringBuffer stringBuffer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_layout);
        stringBuffer = new StringBuffer();
        threadTaskPresenter = new ThreadTaskPresenter(this);
        start_thread_btn = (Button) findViewById(R.id.start_thread_btn);
        close_thread_btn = (Button) findViewById(R.id.close_thread_btn);
        result_txt = (TextView) findViewById(R.id.result_txt);
        result_thread_txt = (TextView) findViewById(R.id.result_thread_txt);
        start_thread_btn.setOnClickListener(this);
        close_thread_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_thread_btn:
                for (int index = 0; index < 10000; index++) {
                    threadTaskPresenter.doTaskPresenter(index);
                }
                break;
            case R.id.close_thread_btn:
                threadTaskPresenter.closeThreadExecutor();
                StringBuffer resultBuffer = new StringBuffer();
                resultBuffer.append("线程池是否关闭" + threadTaskPresenter.getThreadExecutor().getExecutor().isShutdown() + "\n");
                resultBuffer.append("执行线程是否关闭" + threadTaskPresenter.getExecutorRunnable().isCancelled());
                result_thread_txt.setText(resultBuffer);
                threadTaskPresenter.resetThreadExecutor();
                break;
        }
    }


    @Override
    public void doTaskView(String message) {
        stringBuffer.append(message);
        result_txt.setText(stringBuffer.toString());
    }
}
