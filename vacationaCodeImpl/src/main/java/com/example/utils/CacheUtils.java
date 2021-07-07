package com.example.utils;

import java.util.HashMap;

public class CacheUtils {
    public static final HashMap<String,Object> cache = new HashMap<String,Object>();
    public static Object get(String key,Object defaultValue){
        Object obj = cache.get(key);
        return obj == null ? defaultValue : obj;
    }

    public static void put(String key, Object obj){
        cache.put(key, obj);
    }

    public static void remove(String key){
        cache.remove(key);
    }
}
