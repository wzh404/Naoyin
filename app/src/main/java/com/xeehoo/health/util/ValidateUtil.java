package com.xeehoo.health.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WIN10 on 2016/2/4.
 */
public class ValidateUtil {
    public static boolean match(String exp, String val){
        Pattern p = Pattern
                .compile(exp);
        Matcher m = p.matcher(val);
        return m.matches();
    }

    public static boolean isMobileNO(String mobile) {
        return ValidateUtil.match("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", mobile);
    }

    public static boolean isPassword(String pwd){
        if (pwd == null ||
            "".equalsIgnoreCase(pwd.trim()) ||
            pwd.length() < 6 ||
            pwd.length() > 14){
            return false;
        }

        return true;
    }
}
