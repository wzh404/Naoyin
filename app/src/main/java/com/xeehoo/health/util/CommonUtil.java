package com.xeehoo.health.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by WIN10 on 2016/2/4.
 */
public class CommonUtil {
    public static String getInvestDue(String investDay){
        int length = investDay.length();
        String due = investDay.substring(0, length - 1);

        return due;

    }

    public static String getInvestUnit(String investDay){
        int length = investDay.length();
        String unit = investDay.substring(length - 1, length);
        if ("M".equalsIgnoreCase(unit)){
            return "个月";
        }
        else if ("Y".equalsIgnoreCase(unit)){
            return "年";
        }
        else if ("W".equalsIgnoreCase(unit)){
            return "周";
        }
        else if ("D".equalsIgnoreCase(unit)){
            return "天";
        }
        return unit;
    }

    public static String getMoney(String string) {
        Double numDouble = Double.parseDouble(string);
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return format.format(numDouble);
    }
}
