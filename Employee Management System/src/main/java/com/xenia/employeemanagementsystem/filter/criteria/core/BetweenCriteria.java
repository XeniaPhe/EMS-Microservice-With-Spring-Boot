package com.xenia.employeemanagementsystem.filter.criteria.core;

import com.xenia.employeemanagementsystem.exceptions.impl.IllegalOperationException;
import com.xenia.employeemanagementsystem.filter.core.BetweenFilter;
import com.xenia.employeemanagementsystem.filter.core.Filter;
import com.xenia.employeemanagementsystem.filter.core.enums.BoundaryMode;
import com.xenia.employeemanagementsystem.filter.criteria.core.enums.OperationTypeURI;

public class BetweenCriteria<T extends Comparable<T>> extends Criteria<T> {
    private T lower;
    private T upper;
    private OperationTypeURI operationType;

    protected BetweenCriteria(T lower, T upper, OperationTypeURI operationType) {
        this.lower = lower;
        this.upper = upper;
        this.operationType = operationType;
    }

    @Override
    public boolean isEmpty(){
        return false;
    }

    @Override
    public Filter<T> buildFilter() {
        BoundaryMode boundaryMode = operationType == OperationTypeURI.BE ? BoundaryMode.Exclusive
                : operationType == OperationTypeURI.BI ? BoundaryMode.Inclusive : null;

        if(boundaryMode == null)
            throw new IllegalOperationException("Invalid Between Criteria!");

        return BetweenFilter.homogenicBetweenFilter(boundaryMode, lower, upper);
    }
}
