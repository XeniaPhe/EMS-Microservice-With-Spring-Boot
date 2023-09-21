package com.xenia.employeemanagementsystem.filter.criteria.core;

import com.xenia.employeemanagementsystem.exceptions.impl.IllegalOperationException;
import com.xenia.employeemanagementsystem.filter.core.BasicFilter;
import com.xenia.employeemanagementsystem.filter.core.Filter;
import com.xenia.employeemanagementsystem.filter.core.enums.OperationTypeHQL;
import com.xenia.employeemanagementsystem.filter.criteria.core.enums.OperationTypeURI;

public class BasicCriteria<T extends Comparable<T>> extends Criteria<T>{

    private T value;
    private OperationTypeURI operationType;

    BasicCriteria(T value, OperationTypeURI operationType){
        this.value = value;
        this.operationType = operationType;
    }

    @Override
    public boolean isEmpty(){
        return false;
    }

    @Override
    public Filter<T> buildFilter() {
        try {
            return BasicFilter.<T>builder()
                    .operationType(OperationTypeHQL.valueOf(operationType.name()))
                    .value(value)
                    .build();
        } catch (IllegalArgumentException exception){
            throw new IllegalOperationException("Invalid criteria!");
        }
    }
}
