package com.roy.github.learn.javabase.lock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by roy on 2018/11/15.
 */
public class LockDemo {

    private static ReentrantLock lock = new ReentrantLock();
    private static Map<String, Long> lastCreateTimeMap = new HashMap<>();

    public static void main(String[] args) {
//        System.out.println("=====test reentrant=====");
//        for (int i=0;i<10;i++) {
//            Thread thread = new Thread(()->{reentrant();});
//            thread.start();
//        }
        System.out.println("=====test reentrant2=====");
        for (int i=0;i<10;i++) {
            Thread thread = new Thread(()->{reentrant2();});
            thread.start();
        }
//        System.out.println("=====test sync=====");
//        for (int i=0;i<10;i++) {
//            Thread thread = new Thread(()->{sync();});
//            thread.start();
//        }
//        System.out.println("=====test sync2=====");
//        for (int i=0;i<10;i++) {
//            Thread thread = new Thread(()->{sync2();});
//            thread.start();
//        }
//        System.out.println(LockDemo.lastCreateTimeMap.size());
    }

    public static void reentrant() {
        long bgn = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+" begin.");
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" doing.");
            // 业务代码
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
            }
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+" end."+" timecost="+(System.currentTimeMillis()-bgn)/1000);
        }
    }

    public static void reentrant2() {
        long bgn = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+" begin.");
        lock.lock();
        String bizCode = "biz";
        try {
            Long lastCreateTime = lastCreateTimeMap.get(bizCode);
            if(lastCreateTime !=null && lastCreateTime.longValue()!=0&&(new Date().getTime()-lastCreateTime)/1000 < 10){
                // 创建时间在10秒内的就直接不重新生成
                return;
            }
            System.out.println(Thread.currentThread().getName()+" doing.");
            // 业务代码
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
            }
        } finally {
            lock.unlock();
            lastCreateTimeMap.put(bizCode, new Date().getTime());
            System.out.println(Thread.currentThread().getName()+" end."+" timecost="+(System.currentTimeMillis()-bgn)/1000);
        }
    }


    public static void sync() {
        long bgn = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+" begin.");
        synchronized (LockDemo.class){
            System.out.println(Thread.currentThread().getName()+" doing.");
            // 业务代码
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName()+" end."+" timecost="+(System.currentTimeMillis()-bgn)/1000);
    }


    public static void sync2() {
        long bgn = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+" begin.");
        synchronized (LockDemo.class){
            try {
                String bizCode = "biz";
                Long lastCreateTime = lastCreateTimeMap.get(bizCode);
                if(lastCreateTime !=null && lastCreateTime.longValue()!=0&&(new Date().getTime()-lastCreateTime)/1000 < 10){
                    // 创建时间在10秒内的就直接不重新生成
                    return;
                }
                System.out.println(Thread.currentThread().getName()+" doing.");
                // 业务代码
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                }
                lastCreateTimeMap.put(bizCode, new Date().getTime());
            } finally {
                System.out.println(Thread.currentThread().getName()+" end."+" timecost="+(System.currentTimeMillis()-bgn)/1000);
            }
        }
    }

}
