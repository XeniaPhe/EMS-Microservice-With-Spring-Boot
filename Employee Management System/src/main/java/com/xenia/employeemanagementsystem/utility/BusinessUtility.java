package com.xenia.employeemanagementsystem.utility;

public class BusinessUtility {
    private BusinessUtility() {}

    public static boolean isAnyNull(Object... params){
        for (Object param : params){
            if(param == null)
                return true;
        }

        return false;
    }

    public static boolean isNullOrEmpty(String string){
        if(string == null)
            return true;

        return string.isBlank();
    }
}
