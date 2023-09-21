package com.xenia.employeemanagementsystem.utility;

import com.xenia.employeemanagementsystem.exceptions.impl.InvalidValueException;
import com.xenia.employeemanagementsystem.filter.criteria.core.Criteria;
import com.xenia.employeemanagementsystem.filter.criteria.core.enums.OperationTypeURI;

public class CriteriaUtility {
    private CriteriaUtility(){}


    //This method will return an empty criteria if the value is null as opposed to the Criteria.of() method overloads
    public static <U extends Comparable<U>> Criteria<U> getCriteriaOf(U value){
        return value == null ? Criteria.empty() : Criteria.of(value);
    }

    //This method takes in the parameters for a possible homogenic between filter and returns one of empty criteria,
    //basic criteria and between criteria
    public static <U extends Comparable<U>> Criteria<U> getCriteriaOf(U value, U upperValue, OperationTypeURI operationType){
        if(value == null)
            return Criteria.empty();

        if(operationType == null)
            return Criteria.of(value);

        if(!operationType.isBetweenOperation())
            return Criteria.of(value, operationType);

        if(upperValue == null || upperValue.compareTo(value) < 1)
            throw new InvalidValueException("hireDateAfter must be provided and be greater than the first");

        return Criteria.of(value, upperValue, operationType);
    }

    public static boolean isAllCriteriaEmpty(Criteria<?>... criteria){
        for(Criteria<?> criterion : criteria){
            if(!criterion.isEmpty())
                return false;
        }

        return true;
    }
}
