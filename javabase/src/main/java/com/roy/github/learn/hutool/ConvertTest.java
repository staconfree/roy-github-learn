package com.roy.github.learn.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/11/23 0023.
 */
public class ConvertTest {
    public static void main(String[] args) {
        Object[] a = {"a", "你", "好", "", 1};
//从4.1.11开始可以这么用
        List<?> list = Convert.toList(String.class,a);
        String str = "我是一个小小的可爱的字符串";
//结果："e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2"
        String hex = Convert.toHex(str, CharsetUtil.CHARSET_UTF_8);
        System.out.println(hex);
        System.out.println(Convert.hexStrToStr(hex, CharsetUtil.CHARSET_UTF_8));
        String unicode = Convert.strToUnicode(str);
        System.out.println(unicode);
//结果为："我是一个小小的可爱的字符串"
        String raw = Convert.unicodeToStr(unicode);
        System.out.println(raw);
        double d = 67556.32;
//结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
        String digitUppercase = Convert.digitToChinese(d);
        System.out.println(digitUppercase);
    }
}
