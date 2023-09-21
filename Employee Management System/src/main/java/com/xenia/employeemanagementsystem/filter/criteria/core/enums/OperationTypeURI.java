package com.xenia.employeemanagementsystem.filter.criteria.core.enums;

import com.xenia.employeemanagementsystem.exceptions.impl.InvalidValueException;

public enum OperationTypeURI {
    EQ,
    NE,
    GT,
    GTE,
    LT,
    LTE,
    BI, //between inclusive
    BE; //between exclusive


    public static OperationTypeURI tryConvert(String operator){
        try {
            return OperationTypeURI.valueOf(operator.toUpperCase());
        } catch (IllegalArgumentException exception){
            throw new InvalidValueException("Invalid operation type!");
        }
    }

    public boolean isBetweenOperation(){
        return this == BI || this == BE;
    }
}
