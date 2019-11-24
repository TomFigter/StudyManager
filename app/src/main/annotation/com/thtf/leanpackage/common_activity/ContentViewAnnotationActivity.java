package com.thtf.leanpackage.common_activity;

import android.os.Bundle;
import android.widget.Toast;

import com.thtf.apt.once_click_apt.OnceClick;
import com.thtf.leanpackage.R;
import com.thtf.leanpackage.custom_annotation.ContentView;
import com.thtf.onceclickapt.OnceInit;

import org.jetbrains.annotations.Nullable;

/**
 * Created by LiShiChuang on 2018/12/5.
 */
//注解加载布局
@ContentView(R.layout.content_annotation_layout)
//@GetMsg(id = 2, name = "MAIN")
public class ContentViewAnnotationActivity extends BaseAnnotationActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        OnceInit.once(this);
        super.onCreate(savedInstanceState);
    }

    @OnceClick(R.id.annotation_btn)
    public void once() {
        Toast.makeText(this, "点击了我!", Toast.LENGTH_SHORT).show();
    }
}
