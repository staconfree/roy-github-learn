package com.roy.github.learn.javabase.thread;

/**
 * Created by xiexiaoqing1 on 2018/10/31.
 */
public class ThreadTest {

    public static void main(String[] args) {
        join();
    }

    public static void join(){
        Thread thread1 = new Thread(()->{
            System.out.println("--------thread1 start--------");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
            }
            System.out.println("--------thread1 done--------");
        });
        Thread thread2 = new Thread(()->{
            System.out.println("--------thread2 start--------");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
            }
            System.out.println("--------thread2 done--------");
        });
        System.out.println("------main start-----");
        thread1.start();
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
        }
        System.out.println("------main end-----");
    }

}
