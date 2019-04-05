package com.thtf.onceclickapt;

import android.app.Activity;
import android.view.View;

/**
 * Created by LiShiChuang on 2018/12/14.
 */
public enum Finder {
    VIEW {
        @Override
        public View findViewById(Object source, int id) {
            return ((View) source).findViewById(id);
        }
    },
    ACTIVITY {
        @Override
        public View findViewById(Object source, int id) {
            return ((Activity) source).findViewById(id);
        }
    };

    public abstract View findViewById(Object source, int id);
}
