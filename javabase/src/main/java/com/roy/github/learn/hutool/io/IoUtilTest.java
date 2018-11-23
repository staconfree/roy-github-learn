package com.roy.github.learn.hutool.io;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;

/**
 * Created by roy on 2018/11/6.
 */
public class IoUtilTest {

    public static void main(String[] args) throws Exception{
        copyFile();
    }

    public static void copyFile() throws Exception{
        BufferedInputStream in = FileUtil.getInputStream(new File("D:\\data\\test1.txt"));
        BufferedOutputStream out = FileUtil.getOutputStream(new File("D:/data/test2.txt"));
        IoUtil.copy(in,out,IoUtil.DEFAULT_BUFFER_SIZE);
        IoUtil.close(in);
        IoUtil.close(out);
    }


}
