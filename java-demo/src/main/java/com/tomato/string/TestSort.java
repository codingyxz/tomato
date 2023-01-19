package com.tomato.string;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/11/7 3:00 下午
 * @Version V1.0
 **/
public class TestSort {

    public static void main(String[] args) {


        String[] str = new String[]{"ASM-2", "ASM-3", "ASM-1", "浙里办票发票数据企业接入文档V3.0.pdf", "“浙里办票”发票数据企业接入文档V3.0.pdf", "VOSSA-20220630563284-0030-其他附件-1", "线上问题负责模块划分.png"};

        Arrays.sort(str, Collator.getInstance(Locale.CHINA));

        for (String s : str) {
            System.out.println(s);

        }

    }
}
