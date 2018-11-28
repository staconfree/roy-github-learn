package com.roy.github.learn.javabase.juc;

import java.util.concurrent.CountDownLatch;

/**
 * Created by roy on 2018/11/28.
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        startEndCountDown();
    }

    public static void startEndCountDown() {
        Integer n = 5;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(n);
        for (int i=0;i<n;i++) {
            int num = i;
            new Thread(()->{
                try {
                    System.out.println(num+"号选手准备好了,等待裁判员哨声响起..");
                    start.await();
                    System.out.println(num+"号选手出发..");
                    Thread.sleep((long) (Math.random()*10000));
                    System.out.println(num+"号选手到达终点..");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }

            }).start();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("裁判员哨声响起..");
        start.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有运动员到达终点");

    }

}
