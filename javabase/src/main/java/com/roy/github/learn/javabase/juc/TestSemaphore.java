package com.roy.github.learn.javabase.juc;

import java.util.concurrent.Semaphore;

/**
 * Created by roy on 2018/11/28.
 */
public class TestSemaphore {

    public static void main(String[] args) {
        takeSeat();
    }

    public static void takeSeat() {
        Semaphore semaphore = new Semaphore(5);
        for (int i=0;i<10;i++) {
            new TakeSeat(i,semaphore).start();
        }
    }

    static class TakeSeat extends Thread {

        private int num;
        private Semaphore semaphore;

        public TakeSeat(int num, Semaphore semaphore) {
            this.num=num;
            this.semaphore=semaphore;
        }

        public void run() {
            try {
                semaphore.acquire();
                System.out.println("同学"+this.num+"占用一台电脑...");
                Thread.sleep(2000);
                System.out.println("--同学"+this.num+"离开电脑");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
