package com.xenia.employeemanagementsystem.filter.core;

import com.xenia.employeemanagementsystem.filter.core.enums.BoundaryMode;
import com.xenia.employeemanagementsystem.filter.core.enums.LogicalOperator;
import com.xenia.employeemanagementsystem.filter.core.enums.OperationTypeHQL;
import com.xenia.employeemanagementsystem.utility.BusinessUtility;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.Map;

public class BetweenFilter<T extends Comparable<T>> extends Filter<T> {
    public static class Builder<T extends Comparable<T>> extends FilterBuilder<BetweenFilter<T>> {
        private Builder() {
            filter = new BetweenFilter<T>();
        }

        public Builder<T> lowerBound(T lowerBound){
            filter.lowerBound = lowerBound;
            return this;
        }

        public Builder<T> upperBound(T upperBound){
            filter.upperBound = upperBound;
            return this;
        }

        public Builder<T> lowerBoundaryMode(BoundaryMode lowerBoundaryMode){
            filter.lowerBoundaryMode = lowerBoundaryMode;
            return this;
        }

        public Builder<T> upperBoundaryMode(BoundaryMode upperBoundaryMode){
            filter.upperBoundaryMode = upperBoundaryMode;
            return this;
        }

        public Builder<T> negated(boolean negated){
            filter.negated = negated;
            return this;
        }

        public BetweenFilter<T> build(){
            validateFilter();
            return filter;
        }

        private void validateFilter(){
            if(BusinessUtility.isAnyNull(filter.lowerBound, filter.upperBound)) {
                throw new IllegalStateException("lowerBound and upperBound fields must be set before" +
                        " building the BetweenFilter object!");
            }
        }
    }

    private T lowerBound;
    private T upperBound;
    private BoundaryMode lowerBoundaryMode;
    private BoundaryMode upperBoundaryMode;
    private boolean negated;

    public static <U extends Comparable<U>> Builder<U> builder(){
        return new Builder<U>();
    }

    private BetweenFilter(){
        //counter is incremented again because BetweenFilter uses two consecutive id's
        counter++;
        lowerBound = null;
        upperBound = null;
        lowerBoundaryMode = BoundaryMode.Exclusive;
        upperBoundaryMode = BoundaryMode.Exclusive;
        negated = false;
    }

    private Pair<String,String> getParameterNamesForHQL(){
        String base = getFullFieldName(true) + "_";
        return Pair.of(base + id, base + (id + 1));
    }

    @Override
    public String toHQL() {
        Pair<String, String> params = getParameterNamesForHQL();

        OperationTypeHQL lowerOperator = lowerBoundaryMode == BoundaryMode.Inclusive ? OperationTypeHQL.GTE : OperationTypeHQL.GT;
        OperationTypeHQL upperOperator = upperBoundaryMode == BoundaryMode.Inclusive ? OperationTypeHQL.LTE : OperationTypeHQL.LT;
        LogicalOperator logicalOperator = LogicalOperator.AND;

        if(negated){
            lowerOperator = lowerOperator.getOpposite();
            logicalOperator = logicalOperator.getOpposite();
            upperOperator = upperOperator.getOpposite();
        }

         return "(" + getFullFieldName(false) + lowerOperator.getOperator() + ":" + params.getFirst()
                 + logicalOperator.getOperator() + getFullFieldName(false) + upperOperator.getOperator()
                 + ":" + params.getSecond() + ")";
    }

    @Override
    public Map<String, Object> getParameters() {
        Pair<String,String> params = getParameterNamesForHQL();
        return Map.of(params.getFirst(), lowerBound, params.getSecond(), upperBound);
    }

    public static <U extends Comparable<U>> BetweenFilter<U> homogenicBetweenFilter(BoundaryMode mode, U lowerBound, U upperBound){
        return BetweenFilter.<U>builder()
                .lowerBoundaryMode(mode)
                .upperBoundaryMode(mode)
                .lowerBound(lowerBound)
                .upperBound(upperBound)
                .build();
    }
}
