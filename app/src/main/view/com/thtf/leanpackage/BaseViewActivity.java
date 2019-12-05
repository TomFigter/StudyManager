package com.thtf.leanpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.thtf.leanpackage.custom_view.MagicCircle;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.base_view
 * @date 2019-11-28 16:18
 * @描述
 */
public class BaseViewActivity extends Activity {
    private static String TAG = BaseViewActivity.class.getSimpleName();
    private MagicCircle magic_circle;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_view);
        magic_circle=findViewById(R.id.magic_circle);
        magic_circle.startAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "执行onResume");
    }
}
