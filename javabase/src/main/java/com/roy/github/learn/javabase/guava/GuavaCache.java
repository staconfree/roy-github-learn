package com.roy.github.learn.javabase;

import com.google.common.base.Splitter;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/10/29 0029.
 * see https://segmentfault.com/a/1190000011105644
 */
public class GuavaCache {
    public static void main(String[] args) {
        helloGuava();
        expireCacheAfterWrite();
        expireCacheAfterAccess();
        invalidate();
        removeListener();
    }

    public static void helloGuava() {
        System.out.println("=========test helloGuava======");
        Cache<String,String> cache = CacheBuilder.newBuilder().build();
        cache.put("first","hello guava cache");
        System.out.println(cache.getIfPresent("first"));
    }

    public static void expireCacheAfterWrite(){
        System.out.println("=========test expireCacheAfterWrite======");
        Cache<String,String> cache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).build();
        cache.put("first","hello guava cache");
        int time = 1;
        while (true) {
            System.out.println("第"+ time++ + "次取到的值为："+ cache.getIfPresent("first"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            if (time>5){
                break;
            }
        }
    }

    public static void expireCacheAfterAccess(){
        System.out.println("=========test expireCacheAfterAccess======");
        Cache<String,String> cache = CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.SECONDS).build();
        cache.put("first","hello guava cache");
        int time = 1;
        while (true) {
            System.out.println("第"+ time++ + "次取到的值为："+ cache.getIfPresent("first"));
            try {
                Thread.sleep(1000*(time-1));
            } catch (InterruptedException e) {
            }
            if (time>5){
                break;
            }
        }
    }

    public static void invalidate() {
        System.out.println("=========test invalidate======");
        Cache<String,String> cache = CacheBuilder.newBuilder().build();
        cache.put("first","hello guava cache");
        cache.put("key1","value1");
        cache.put("key2","value2");

        List<String> list = Splitter.on(",").splitToList("key1,key2");
        cache.invalidateAll(list);
        System.out.println(cache.getIfPresent("first"));
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
    }


    public static void removeListener() {
        System.out.println("=========test removeListener======");
        RemovalListener<String,String> listener = new RemovalListener<String, String>() {
            @Override
            public void onRemoval(RemovalNotification<String, String> notification) {
                System.out.println("["+notification.getKey()+":"+notification.getValue()+"] is removed!");
            }
        };
        Cache<String,String> cache = CacheBuilder.newBuilder().maximumSize(3).removalListener(listener).build();
        Map<String,String> map = Splitter.on("&").withKeyValueSeparator("=").split("key1=value1&key2=value2&key3=value3&key4=value4&key5=value5&key6=value6");
        cache.putAll(map);
    }



}
