package com.tomato.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zhxy
 * @Date 2023/3/22 11:33 上午
 * @Version V1.0
 **/
public class RegexTest {


    private static final String SPECIAL_CHARACTERS = "[{}\\[\\]()\\\\.^$|?*+（）一杰]";

    public static String escapeSpecialCharacters(String input) {
        Pattern pattern = Pattern.compile(SPECIAL_CHARACTERS);
        Matcher matcher = pattern.matcher(input);
        StringBuffer stringBuffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "\\\\" + matcher.group(0));
        }

        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static String escapeChineseCharacters(String input) {
        String regex = "([\u4E00-\u9FA5]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        StringBuffer stringBuffer = new StringBuffer();

        while (matcher.find()) {
            System.out.println(matcher.group(0));
            matcher.appendReplacement(stringBuffer, escapeSpecialCharacters(matcher.group(0)));
        }

        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static void main(String[] args) {


        System.out.println(escapeChineseCharacters("ad\u4E00\u9FA5gc[}{{（）"));
//        System.out.println(escapeChineseCharacters("云杰"));
    }


}
