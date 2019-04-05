package com.thtf.leanpackage.event_bus_package;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

//import com.example.eventbus.EventBus;
//import com.example.eventbus.Subscriber;
import com.thtf.leanpackage.R;
import com.thtf.leanpackage.event_bus_package.bean.MESSAGE_DTO;


/**
 * Created by LiShiChuang on 2018/12/20.
 */
public class EvenBusServerActivity extends Activity {
    private TextView receiveEventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_bus_server_layout);
        receiveEventBus = (TextView) findViewById(R.id.receive_event_bus);
//        EventBus.getDefault().registerSticky(this);
    }

//    @Subscriber
//    public void onReceiveEventBusMessage(MESSAGE_DTO Message) {
//        receiveEventBus.setText(Message.toString());
//        Log.i("接收到的数据", Message.toString());
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
