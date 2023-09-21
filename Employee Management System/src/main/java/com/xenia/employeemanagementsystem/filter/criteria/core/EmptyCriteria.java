package com.xenia.employeemanagementsystem.filter.criteria.core;

import com.xenia.employeemanagementsystem.exceptions.impl.IllegalOperationException;
import com.xenia.employeemanagementsystem.filter.core.Filter;

public class EmptyCriteria<T extends Comparable<T>> extends Criteria<T> {
    @Override
    public boolean isEmpty() {
        return true;
    }
    @Override
    public Filter<T> buildFilter() {
        throw new IllegalOperationException("The criteria is empty!");
    }
}
