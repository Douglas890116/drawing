package com.interview.utils;

import java.util.regex.Pattern;

public class Tools {
    /**
     * 判斷大於0的數字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("^[1-9][0-9]*");
        return pattern.matcher(str).matches();
    }
}