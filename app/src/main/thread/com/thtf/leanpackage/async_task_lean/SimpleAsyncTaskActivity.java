package com.thtf.leanpackage.async_task_lean;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.thtf.leanpackage.MineApplication;
import com.thtf.leanpackage.R;

/**
 * Created by LiShiChuang on 2018/11/21.
 */
public class SimpleAsyncTaskActivity extends Activity {
    private TextView name_txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_async_task_layout);
        name_txt = (TextView) findViewById(R.id.name_txt);
        name_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initSimpleAsyncTask();
    }

    private void initSimpleAsyncTask() {
        new SimpleAsyncTask<String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.i("SimpleAsyncTask -> ", "onPreExecute");
            }

            @Override
            protected String doInBackground() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("SimpleAsyncTask -> ", "doInBackground");
                return "Hello Chuang";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("SimpleAsyncTask -> ", "onPostExecute");
                name_txt.setText(s);
            }
        }.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MineApplication.getLeakCanary().watch(this);
    }
}
