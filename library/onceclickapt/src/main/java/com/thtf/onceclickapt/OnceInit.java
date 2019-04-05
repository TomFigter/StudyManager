package com.thtf.onceclickapt;

import android.app.Activity;
import android.view.View;

import com.thtf.apt.once_click_apt.ProxyInfo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by LiShiChuang on 2018/12/14.
 */
public class OnceInit {
    private static final Map<Class<?>, AbstractInjector<Object>> INJECTORS = new LinkedHashMap<>();
    private static final long INTERVAL_TIME = 2000;

    public static void once(Activity activity, long intervalTime) {
        AbstractInjector<Object> injector = findInjector(activity);
        injector.inject(Finder.ACTIVITY, activity, activity);
        injector.setIntervalTime(intervalTime);
    }

    public static void once(View view, long intervalTime) {
        AbstractInjector<Object> injector = findInjector(view);
        injector.inject(Finder.VIEW, view, view);
        injector.setIntervalTime(intervalTime);
    }

    public static void once(Activity activity) {
        once(activity, INTERVAL_TIME);
    }

    public static void once(View view) {
        once(view, INTERVAL_TIME);
    }

    public static AbstractInjector<Object> findInjector(Object activity) {
        Class<?> clazz = activity.getClass();
        AbstractInjector<Object> injector = INJECTORS.get(clazz);
        if (injector == null) {
            try {
                Class injectorClazz = Class.forName(clazz.getName() + "$$" + ProxyInfo.PROXY);
                injector = (AbstractInjector<Object>) injectorClazz.newInstance();
                INJECTORS.put(clazz, injector);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return injector;
    }
}
