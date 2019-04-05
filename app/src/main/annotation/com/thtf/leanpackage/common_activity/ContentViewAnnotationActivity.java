package com.thtf.leanpackage.common_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.thtf.apt.once_click_apt.OnceClick;
import com.thtf.leanpackage.R;
import com.thtf.leanpackage.custom_annotation.ContentView;
import com.thtf.onceclickapt.OnceInit;

/**
 * Created by LiShiChuang on 2018/12/5.
 */
//注解加载布局
@ContentView(R.layout.content_annotation_layout)
//@GetMsg(id = 2, name = "MAIN")
public class ContentViewAnnotationActivity extends BaseAnnotationActivity {

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        OnceInit.once(this);
        super.onCreate(savedInstanceState);
        button = (Button) findViewById(R.id.annotation_btn);
    }

    @OnceClick(R.id.annotation_btn)
    public void once() {
        Toast.makeText(this, "点击了我!", Toast.LENGTH_SHORT).show();
    }
}
