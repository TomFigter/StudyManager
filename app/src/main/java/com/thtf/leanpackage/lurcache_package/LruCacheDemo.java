package com.thtf.leanpackage.lurcache_package;


import android.widget.LinearLayout;

import com.bumptech.glide.util.LruCache;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.lurcache_package
 * @date 2019-11-28 09:33
 * @描述
 */
public class LruCacheDemo {
    public static void main(String[] args) {
        LruCache<Integer, String> lruCache = new LruCache<>(16);
        for (int index = 0; index < 20; index++) {
            lruCache.put(index, "李士闯-->" + index);
        }
        System.out.println("LruCacheCount --> " + lruCache.getCurrentSize());
//        lruCache.clearMemory();
//        for (int index = 0; index < 20; index++) {
//            System.out.println("获取的值-->" + lruCache.get(index));
//        }
        lruCache.get(11);
        lruCache.get(5);
        lruCache.get(9);
        System.out.println("LinkedHashMap"+lruCache);
    }
}
