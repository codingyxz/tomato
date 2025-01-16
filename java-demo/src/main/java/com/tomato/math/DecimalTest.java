package com.tomato.math;

import org.junit.Test;

import java.math.BigDecimal;

public class DecimalTest {


    @Test
    public void testDecimal(){

        BigDecimal decimal = new BigDecimal("0.25");
        final BigDecimal decimal2 = new BigDecimal("2");
        final StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while(true){
            i++;
            System.out.print("(" +i + ") ");
            System.out.print(decimal.toString() + "乘以2=");
            decimal = decimal.multiply(decimal2);
            System.out.print(decimal.toString());
            String s = decimal.toString();
            String z = s.substring(0, s.indexOf("."));
            stringBuffer.append(z);
            String x = s.substring(s.indexOf(".")+1);
            decimal = new BigDecimal("0." + x);
            System.out.println(" 整数=" + z + " 小数=" + decimal.toString());
            if(Integer.parseInt(x) == 0)
                break;
        }
        System.out.print("转化为二进制:0." + stringBuffer.toString());
    }
}
