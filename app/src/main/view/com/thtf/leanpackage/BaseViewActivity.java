package com.thtf.leanpackage.base_view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.thtf.leanpackage.R;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.base_view
 * @date 2019-11-28 16:18
 * @描述
 */
public class BaseViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_view);
    }
}
