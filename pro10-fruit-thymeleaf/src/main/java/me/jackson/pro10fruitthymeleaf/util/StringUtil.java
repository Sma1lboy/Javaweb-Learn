package me.jackson.pro10fruitthymeleaf.util;

public class StringUtil {
    public static boolean isEmpty(String s){
        return s == null || "".equals(s);
    }
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}
