package com.roy.github.learn.hutool;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;

/**
 * Created by Administrator on 2018/11/23 0023.
 *
 * 深度克隆使用：ObjectUtil.cloneByStream(obj)
 */
public class CloneTest {
    public static void main(String[] args) {
        // 接口实现
        Cat cat = new Cat();
        System.out.println("==cat:=="+cat+"==nameAndAge:=="+cat.name+","+cat.age);
        Cat cloneCat = cat.clone();
        System.out.println("==cloneCat:=="+cloneCat+"==nameAndAge:=="+cloneCat.name+","+cloneCat.age);
        // 继承实现
        Dog dog = new Dog();
        System.out.println("==dog:=="+dog+"==nameAndAge:=="+dog.name+","+dog.age);
        Dog cloneDog = dog.clone();
        System.out.println("==cloneDog:=="+cloneDog+"==nameAndAge:=="+cloneDog.name+","+cloneDog.age);

    }

    private static class Cat implements Cloneable<Cat>{
        private String name = "miaomiao";
        private int age = 2;

        @Override
        public Cat clone() {
            try {
                return (Cat) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new CloneRuntimeException(e);
            }
        }
    }

    private static class Dog extends CloneSupport<Dog> {
        private String name = "wangwang";
        private int age = 3;
    }

}
