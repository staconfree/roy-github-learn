package com.roy.github.learn.javabase.statictest;

/**
 * Created by roy on 2018/11/14.
 */
public class B {

    public static void main(String[] args) {
        A a = new A();
        a.setMap("1","123");
        B b = new B();
        System.out.println(b.getMap("1"));
    }

    public String getMap(String key){
        return A.map.get(key);
    }

}
