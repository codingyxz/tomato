package com.tomato.ccy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 * @Desc TODO
 * @Author zhxy
 * @Date 2022/12/7 3:32 下午
 * @Version V1.0
 **/
public class CurrencyTest {


    public static void main(String[] args) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-mm-dd");

        System.out.println(simpleDateFormat.format(new Date()));


//        for (Currency ccy : Currency.getAvailableCurrencies()) {
//            System.out.println(ccy.getCurrencyCode() + "-" + ccy.getDisplayName() + "-" + ccy.getSymbol() );
//        }

    }
}
