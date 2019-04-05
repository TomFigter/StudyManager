package com.thtf.onceclickapt;

/**
 * Created by LiShiChuang on 2018/12/14.
 *
 * @param <T>
 */
public interface AbstractInjector<T> {
    /**
     * 注射代码
     *
     * @param finder
     * @param target
     * @param source
     */
    void inject(Finder finder, T target, Object source);

    /**
     * 设置间隔时间
     *
     * @param time
     */
    void setIntervalTime(long time);
}
