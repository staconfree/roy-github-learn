package com.roy.github.learn.javabase.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by roy on 2018/12/13.
 */
public class JdkProxy {

    public static void main(String[] args) {
        CustomizeHandler handler = new CustomizeHandler(ImplA.class);
        InterfaceA impl = (InterfaceA) Proxy.newProxyInstance(JdkProxy.class.getClassLoader(),new Class[]{InterfaceA.class},handler);
        impl.say();
    }



    static class ImplA implements InterfaceA {
        @Override
        public void say() {
            System.out.println("------impleA--------");
        }
    }

    static class ImplB implements InterfaceA {

        @Override
        public void say() {
            System.out.println("------implB------");
        }
    }

    interface InterfaceA {
        void say();
    }

    static class CustomizeHandler implements InvocationHandler {

        private Object target;
        public CustomizeHandler(Class clazz){
            try {
                target = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            before();
            Object obj= method.invoke(target,args);
            after();
            return obj;
        }

        private void after() {
            System.out.println("====do after====");
        }

        private void before() {
            System.out.println("====do before===");
        }

    }

}
