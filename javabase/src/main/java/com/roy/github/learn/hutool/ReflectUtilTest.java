package com.roy.github.learn.hutool;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Method;

/**
 * Created by roy on 2018/11/24 0024.
 */
public class ReflectUtilTest {

    private int i;
    private String name;
    public void setI(int i){
        this.i = i;
    }

    public void setI(int i,String name){
        this.i = i;
        this.name=name;
    }

    public ReflectUtilTest(){

    }
    public ReflectUtilTest(int i){
        this.i=i;
    }

    public static void main(String[] args) {
        newInstance();
        invokeMethod();
    }

    public static void newInstance() {
        ReflectUtilTest reflectUtilTest = ReflectUtil.newInstance(ReflectUtilTest.class);
        System.out.println(reflectUtilTest.i);
        reflectUtilTest = ReflectUtil.newInstance(ReflectUtilTest.class,123);
        System.out.println(reflectUtilTest.i);
    }

    public static void invokeMethod(){
        ReflectUtilTest reflectUtilTest = new ReflectUtilTest();
        ReflectUtil.invoke(reflectUtilTest,"setI",1234);
        System.out.println(reflectUtilTest.i);
        Method method = ReflectUtil.getMethod(ReflectUtilTest.class,"setI",int.class,String.class);
        ReflectUtil.invoke(reflectUtilTest,method,12345,"test");
        System.out.println(reflectUtilTest.i);
    }

}
