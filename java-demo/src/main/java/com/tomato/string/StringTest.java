package com.tomato.string;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/8/17 4:17 下午
 * @Version V1.0
 **/
public class StringTest {

    public static void main(String[] args) {


        BigDecimal bigDecimal = new BigDecimal("200.0");
        System.out.println(bigDecimal);
        System.out.println(bigDecimal.toPlainString());
        BigDecimal bigDecimal1 = new BigDecimal("200.0");
        System.out.println(bigDecimal1.stripTrailingZeros().toPlainString());

        System.out.println(bigDecimal.equals(bigDecimal1));

//
//        Object str = null;
//
//        System.out.println((String)str);
//        System.out.println(String.valueOf(str));
//
//        System.out.println("null".equals((String)str));
//        System.out.println("null".equals(String.valueOf(str)));
//        System.out.println(String.valueOf(str) instanceof String);



//
//        LocalDateTime now = LocalDateTime.now();
//        now = now.plusHours(15);
//        System.out.println(now.getDayOfYear());
//
//
//        long total = 2090;
//        int page = 1000;
//
//        double ceil = Math.ceil(total / page);
//
//        System.out.println(ceil);
//
//
//        String[] pieceArr = new String[4];
//
//        pieceArr[0] = "aa";
//        pieceArr[1] = "2022-01-01";
//        pieceArr[3] = "12345";
//
////        System.out.println(String.format("%s_%s_%s_%s",pieceArr[0],pieceArr[1],pieceArr[2],pieceArr[3]));
//
//
//        List<String> collect = Arrays.stream(pieceArr)
//                .filter(piece -> piece != null)
//                .collect(Collectors.toList());
//        System.out.println(String.join("_", collect));


//        System.out.println(String.format("temp%s%s.pdf", File.separator, "123"));
//
//
//        Object str = null;
//        System.out.println(String.valueOf(str));
//
//
//        System.out.println(StringUtils.isEmpty(String.valueOf(str)));
//
//
//        System.out.println((String) str);


    }


}
