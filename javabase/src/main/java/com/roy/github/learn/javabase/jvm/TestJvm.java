package com.roy.github.learn.javabase.jvm;

/**
 * Created by roy on 2018/12/13.
 */
public class TestJvm {

    public static void main(String[] args) {
        string();
    }

    private static void string() {
        String A="abc";
        String B="abc";
        System.out.println(A==B);
        System.out.println(A.equals(B));
    }
}
