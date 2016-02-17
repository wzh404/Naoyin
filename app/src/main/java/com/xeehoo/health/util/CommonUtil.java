package com.xeehoo.health.util;

import android.content.Context;
import android.util.Log;

import com.google.repacked.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

    public static String getMoney(BigDecimal money){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return format.format(money);
    }

    public static String getMoney(String val) {
        if (CommonUtil.isEmpty(val)){
            return "0.00";
        }

        BigDecimal decimal = new BigDecimal(val);
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return format.format(decimal);
    }

    public static String getMoney2(String val){
        if (CommonUtil.isEmpty(val)){
            return "0.00";
        }

        BigDecimal decimal = new BigDecimal(val).divide(new BigDecimal(100.0)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return format.format(decimal);
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     *
     * @return
     */
    public static String tomorrow(){
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        gc.add(GregorianCalendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(gc.getTime());
    }

    /**
     * 获取当前日期yyyymmdd
     *
     * @return
     */
    public static String getCurrentDate(){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(dt);
    }

    /**
     * 利息计算 = 本金 * 年化率 * (天数 / 360)
     *
     * @param amount
     * @param rate
     * @param days
     * @return
     */
    public static BigDecimal calculateInterest(BigDecimal amount, BigDecimal rate, long days){
        return amount.multiply(rate).multiply(new BigDecimal(days / 360.0 / 100.0));
    }

    public static Date getDate(String strDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 计算结息日期
     *
     * @param date
     * @param investDay
     * @return
     */
    public static Date calculateInvestClosingDate(Date date, String investDay){
        int length = investDay.length();

        String due = investDay.substring(0, length - 1);
        String unit = investDay.substring(length - 1, length);

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);

        int step = 0;
        if ("Y".equalsIgnoreCase(unit)){
            step = Integer.parseInt(due);
            gc.add(GregorianCalendar.YEAR, step);
        }
        if ("M".equalsIgnoreCase(unit)){
            step = Integer.parseInt(due);
            gc.add(GregorianCalendar.MONTH, step);
        }
        if ("W".equalsIgnoreCase(unit)){
            step = Integer.parseInt(due);
            gc.add(GregorianCalendar.WEEK_OF_YEAR, step);
        }
        if ("D".equalsIgnoreCase(unit)){
            step = Integer.parseInt(due);
            gc.add(GregorianCalendar.DATE, step);
        }

        return gc.getTime();
    }

    public static int diffDate(Date fromDay, Date toDay) {

        long from = fromDay.getTime();
        long to = toDay.getTime();
        return (int) TimeUnit.MILLISECONDS.toDays(to - from);
    }
}
