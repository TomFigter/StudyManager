package com.thtf.leanpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.base_view
 * @date 2019-11-28 16:18
 * @描述
 */
public class BaseViewActivity extends Activity {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_view);
    }
}
