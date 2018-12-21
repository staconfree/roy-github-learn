package com.roy.github.learn.javabase.array;

import java.util.Arrays;

/**
 * 数组操作
 * Created by roy on 2018/12/20.
 */
public class ArrayOperate {

    // 数组遍历
    // 数组剔除
    // 数组新增
    // 数组扩容
    // 数组复制

    private static Integer[] arr;
    private static int initSize = 10;

    public static void main(String[] args) {
        // 初始化数据
        init();
        printArr();
        // 循环遍历
        clearBetween(8,2);
        printArr();
        // 数组扩容
        addCapacity(1);
        printArr();
        // 数组剔除其中一个元素
        remove(9);
        printArr();
    }

    private static void remove(int index) {
        int size = arr.length;
        int numMoved = arr.length-index-1;
        System.arraycopy(arr,index+1,arr,index,numMoved);
        arr[--size]=null;
    }

    private static void addCapacity(int i) {
        // 模仿ArrayList，先扩容1/2，如果不够，则用扩容的大小。
        int oldCapacity = arr.length;
        int newCapacity = arr.length+(arr.length>>1);
        if (newCapacity<oldCapacity+i){
            newCapacity=oldCapacity+i;
        }
        arr= Arrays.copyOf(arr,newCapacity);// Arrays.copyOf 里面包含数组的初始化方法，Array.newInstance(newType.getComponentType(), newLength)
    }

    private static void printArr() {
        String result = "";
        for (int i=0;i<arr.length;i++){
            if (i==0){
                result+=arr[i];
            } else {
                result+=","+arr[i];
            }
        }
        System.out.println(result);
    }

    private static void init() {
        arr = new Integer[initSize];
        for (int i = 0; i < initSize; i++) {
            arr[i] = 1;
        }
    }

    /**
     * 循环遍历数组，把fromIndex到toIndex之间的数清0，不包含fromIndex和toIndex
     * @param fromIndex
     * @param toIndex
     */
    private static void clearBetween(int fromIndex, int toIndex) {
        for (int index = (fromIndex + 1) % arr.length; index != toIndex; index = (index + 1) % arr.length) {
            arr[index]=0;
        }
    }

}
