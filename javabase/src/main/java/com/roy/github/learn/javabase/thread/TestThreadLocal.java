package com.roy.github.learn.javabase.thread;

import java.lang.ref.WeakReference;

/**
 * TODO 线程变量,WeakReference
 * Created by roy on 2018/12/4.
 * ThreadLocal内存溢出的原因：https://blog.csdn.net/bntx2jsqfehy7/article/details/78315161
 */
public class TestThreadLocal {

    public static void main(String[] args) {
        ThreadDemo thread = new ThreadDemo();
        ThreadLocalDemo<String> threadLocal = new ThreadLocalDemo<String>();
        threadLocal.set(thread,"12345");// 真实场景中,是不需要传thread的,因为可以通过Thread.currentThread()来获取
        System.out.println(threadLocal.get(thread));
        ThreadDemo thread2 = new ThreadDemo();
        threadLocal.set(thread2,"54534");
        System.out.println(threadLocal.get(thread2));
    }

    static class ThreadLocalDemo<T>{

        static class ThreadLocalMap {

            static class Entry extends WeakReference<ThreadLocalDemo<?>> {
                /** The value associated with this ThreadLocal. */
                Object value;

                Entry(ThreadLocalDemo<?> k, Object v) {
                    super(k);
                    value = v;
                }
            }

            private Entry[] table;

            private void set(ThreadLocalDemo<?> key, Object value) {
                if (table == null){
                    table=new Entry[16];
                }
                // 放入entry,真正寻址没那么简单
                int hashCode = key.hashCode();
                table[hashCode%16]=new Entry(key,value);
            }

            private Object get(ThreadLocalDemo<?> key) {
                int hashCode = key.hashCode();
               return ((Entry)table[hashCode%16]).value;
            }

        }

        public void set(ThreadDemo thread,T value){
            ThreadLocalDemo.ThreadLocalMap map = thread.threadLocals;
            if (map==null){
                thread.threadLocals = new ThreadLocalMap();
                thread.threadLocals.set(this,value);
            }else {
                map.set(this,value);
            }
        }

        public T get(ThreadDemo thread){
           return (T)thread.threadLocals.get(this);
        }

    }



    static class ThreadDemo {

        public ThreadLocalDemo.ThreadLocalMap threadLocals=null;

    }

}
