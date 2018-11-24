package com.roy.github.learn.hutool;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Created by roy on 2018/11/24 0024.
 */
public class ClassUtilTest {

    protected String name;
    private void setName(String name){
        this.name=name;
    }

    public static void main(String[] args) {
        System.out.println(ClassUtil.getClassPath());
        System.out.println(ClassUtil.getClassLoader());
        System.out.println(ClassUtil.getContextClassLoader());
        System.out.println("=====bgn=====");
        new TypeDemo();
        System.out.println("=====end=====");
        System.out.println(ClassUtil.getTypeArgument(TypeDemo.class));
        System.out.println(ClassUtil.getTypeArgument(TypeDemo.class,1));
        System.out.println(ClassUtil.isBasicType(Integer.class));
        System.out.println(ClassUtil.isBasicType(String.class));
        System.out.println(ClassUtil.scanPackage());
        System.out.println(ClassUtil.isAllAssignableFrom(new Class[]{Base.class},new Class[]{TypeDemo.class}));
        System.out.println(ClassUtil.isAssignable(Base.class,TypeDemo.class));
        Method method = ReflectUtil.getMethod(ClassUtilTest2.class,"setName",String.class);
//        ClassUtil.setAccessible(method);
        ClassUtilTest2 ClassUtilTest2 = new ClassUtilTest2();
//        ReflectUtil.invoke(ClassUtilTest2,method,"classUtilTest");// ReflectUtil.invoke会自动调用setAccessible方法
        try {
            method.invoke(ClassUtilTest2,"classUtilTest");// 发现即使不setAccessible，此地方一样能将值反射进去
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(ClassUtilTest2.name);
    }

    private static class ClassUtilTest2 extends ClassUtilTest {

    }

    private static class Base<T1,T2> {
        class A {}
        class B extends A {}
        public Base (){
            Type type = getClass().getGenericSuperclass();
            System.out.println("getClass() == " + getClass());
            System.out.println("type = " + type);
            Type trueType = ((ParameterizedType)type).getActualTypeArguments()[0];
            System.out.println("trueType1 = " + trueType);
            trueType = ((ParameterizedType)type).getActualTypeArguments()[1];
            System.out.println("trueType2 = " + trueType);

            B t = new B();
            type = t.getClass().getGenericSuperclass();
            System.out.println("type="+type);
            System.out.println("A is B's super class :" + ((ParameterizedType)type).getActualTypeArguments().length);
        }
    }

    private static class TypeDemo extends Base<String,Integer> {

    }

}
