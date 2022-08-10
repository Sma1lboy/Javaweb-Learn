package me.jackson.pro17fruitthymeleaf.util;

public class IntegerUtil {
    public static boolean parseInteger(String s){
        if(StringUtil.isNotEmpty(s)) {
            try {
                int a = Integer.parseInt(s);
            } catch (NumberFormatException e) {

                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }
}
