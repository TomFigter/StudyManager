package com.thtf.leanpackage.common_activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.thtf.leanpackage.R;
import com.thtf.leanpackage.custom_annotation.ContentView;

import org.jetbrains.annotations.Nullable;

/**
 * Created by LiShiChuang on 2018/12/5.
 */

public abstract class BaseAnnotationActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.content_annotation_layout);
        //注解解析
        annotationContentView();
    }


    /**
     * 1>遍历所有子类
     * <p>
     * 2>找到修饰了注解ContentView的类
     * <p>
     * 3>获取ContentView的属性值
     * <p>
     * 4>为Activity设置布局
     */
    private void annotationContentView() {
        for (Class clzz = this.getClass(); clzz != Context.class; clzz = clzz.getSuperclass()) {
            ContentView annotation = (ContentView) clzz.getAnnotation(ContentView.class);
            if (annotation != null) {
                try {
                    this.setContentView(annotation.value());
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }
}
