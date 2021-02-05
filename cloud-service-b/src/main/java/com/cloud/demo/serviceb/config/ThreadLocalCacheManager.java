package com.cloud.demo.serviceb.config;

import java.util.Map;

/**
 * @ClassName ThreadLocalCacheManager
 * @Author JackZhou
 * @Date 2021/2/1  15:29
 **/
public class ThreadLocalCacheManager {

    private static ThreadLocal<Map> threadLocalCache = new ThreadLocal<>();

    public static Map getCache() {
        return threadLocalCache.get();
    }

    public static void setCache(Map value) {
        threadLocalCache.set(value);
    }

    public static void removeCache() {
        threadLocalCache.remove();
    }

    public static void removeCache(String key) {
        Map cache = threadLocalCache.get();
        if (cache != null) {
            cache.remove(key);
        }
    }
}
