package com.roy.github.learn.javabase.statictest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roy on 2018/11/14.
 */
public class A {
    public static Map<String,String> map = new HashMap<>();

    public void setMap(String key,String value){
        map.put(key,value);
    }

}
