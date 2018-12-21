package com.roy.github.learn.javabase.classload;

/**
 * Created by roy on 2018/12/6.
 *
 * JVM 虚拟机执行 class 字节码的过程可以分为七个阶段：加载、验证、准备、解析、初始化、使用、卸载。
 * https://mp.weixin.qq.com/s/16MKwhwPHVNezxS5ygk-cA
 */
public class InitializationDemo {

    public static void main(String[] args)
    {
        // factor属性在Factor里面，不需要Son进行初始化
        System.out.println("爸爸的岁数:" + Son.factor);
        // 当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
//        new Son();
    }
    static class Grandpa
    {
        static
        {
            System.out.println("爷爷在静态代码块");
        }
    }
    static class Father extends Grandpa
    {
        static
        {
            System.out.println("爸爸在静态代码块");
        }
        public static int factor = 25;
        public Father()
        {
            System.out.println("我是爸爸~");
        }
    }
    static class Son extends Father
    {
        static
        {
            System.out.println("儿子在静态代码块");
        }
        public Son()
        {
            System.out.println("我是儿子~");
        }
    }

}
