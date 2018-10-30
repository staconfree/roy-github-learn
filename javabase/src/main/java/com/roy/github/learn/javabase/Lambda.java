package com.roy.github.learn.javabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/29 0029.
 */
public class Lambda {
    public static void main(String[] args) {
        threadLambda();
        listLambda();
    }

    private static void listLambda() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.forEach(n-> System.out.println(n));
        System.out.println("========");
        list.stream().map((x)->x+2).forEach(System.out::println);
        System.out.println("--------");
        list.forEach(n-> System.out.println(n));
        int sum = list.stream().map((x)->x*x).reduce((x,y)->x+y).get();// 1*1+2*2+3*3+4*4
        System.out.println(sum);

    }

    private static void threadLambda() {
        Thread thread = new Thread(()->{
            System.out.println("线程启动");
        });
        thread.start();
    }

}
