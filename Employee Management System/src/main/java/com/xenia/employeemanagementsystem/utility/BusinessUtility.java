package com.xenia.employeemanagementsystem.utility;

import com.xenia.employeemanagementsystem.dto.response.DepartmentResponse;
import com.xenia.employeemanagementsystem.dto.response.EmployeeResponse;
import com.xenia.employeemanagementsystem.dto.response.IEntityResponse;
import com.xenia.employeemanagementsystem.dto.response.TaskResponse;

import java.lang.reflect.ParameterizedType;

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
