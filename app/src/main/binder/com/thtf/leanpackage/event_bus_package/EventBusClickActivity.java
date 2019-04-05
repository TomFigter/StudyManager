package com.thtf.leanpackage.event_bus_package;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

//import com.example.eventbus.EventBus;
import com.thtf.leanpackage.R;
import com.thtf.leanpackage.event_bus_package.bean.MESSAGE_DTO;


/**
 * Created by LiShiChuang on 2018/12/20.
 */
public class EventBusClickActivity extends Activity implements View.OnClickListener {
    private TextView eventBusResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_bus_click_layout);
        findViewById(R.id.send_event_bus_btn).setOnClickListener(this);
        eventBusResult = (TextView) findViewById(R.id.event_bus_result);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_event_bus_btn:
                MESSAGE_DTO message_dto = new MESSAGE_DTO();
                message_dto.setName("李士闯");
                message_dto.setAge(24);
                message_dto.setSayInfo("又到年底了=_=!");
//                EventBus.getDefault().postSticky(message_dto);
                Intent intent = new Intent(this, EvenBusServerActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}

