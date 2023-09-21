package com.xenia.employeemanagementsystem.filter.criteria.core;

import com.xenia.employeemanagementsystem.filter.core.Filter;
import com.xenia.employeemanagementsystem.filter.criteria.core.enums.OperationTypeURI;

public abstract class Criteria<T extends Comparable<T>> {
    public abstract boolean isEmpty();
    public abstract Filter<T> buildFilter();

    public static <U extends Comparable<U>> Criteria<U> of(U value){
        return new BasicCriteria(value, OperationTypeURI.EQ);
    }

    public static <U extends Comparable<U>> Criteria<U> of(U value, OperationTypeURI operationType){
        return new BasicCriteria(value, operationType);
    }

    public static <U extends Comparable<U>> Criteria<U> of(U value, U secondValue){
        return new BetweenCriteria(value, secondValue, OperationTypeURI.BE);
    }

    public static <U extends Comparable<U>> Criteria<U> of(U value, U secondValue, OperationTypeURI operationType){
        return new BetweenCriteria(value, secondValue, operationType);
    }

    public static <U extends Comparable<U>> Criteria<U> empty(){
        return new EmptyCriteria();
    }
}
