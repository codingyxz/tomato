package com.tomato.num;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TestNum {

    public static void main(String[] args) {

        String str = "%s / %s = %s ≈ %s";
        DecimalFormat df1 =  new DecimalFormat("0.0%");
        DecimalFormat df2 =  new DecimalFormat("0.0%");
        for (int i = 1; i <= 100; i++) {

            BigDecimal decimal1 = BigDecimal.valueOf(i);
            BigDecimal decimal2 = BigDecimal.valueOf(i+100);
            BigDecimal divide1 = decimal1.divide(decimal2,3, RoundingMode.HALF_UP);
            BigDecimal divide2 = decimal1.divide(decimal2,2, RoundingMode.HALF_UP);

            System.out.println(String.format(str,i,decimal2.toString(),df1.format(divide1),df2.format(divide2)));
        }

    }
}
