package com.roy.github.learn.hutool.io;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * Created by roy on 2018/11/6.
 */
public class FileUtilTest {

    public static void main(String[] args) {
        touch();
        ls();
    }

    public static void touch(){
        // touch如果没有文件夹，会自动先创建文件夹
        FileUtil.touch(new File("D:/data/touch.txt"));
    }

    public static void ls() {
        File[] ls = FileUtil.ls("D:/data");
        if (ls!=null){
            for (File file:ls){
                System.out.println(file.getPath());
                if (!FileUtil.isDirectory(file)){
                    // 此处发现这个getType不是很准确，txt的文件类型解析不出来
                    System.out.println(FileUtil.getType(file));
                }
            }
        }
    }



}

