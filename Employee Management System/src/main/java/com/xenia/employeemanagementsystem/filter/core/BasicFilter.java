package com.xenia.employeemanagementsystem.filter.core;

import com.xenia.employeemanagementsystem.filter.core.enums.OperationTypeHQL;
import com.xenia.employeemanagementsystem.utility.BusinessUtility;

import java.util.Map;

public class BasicFilter<T extends Comparable<T>> extends Filter<T>{
    public static class Builder<T extends Comparable<T>> extends FilterBuilder<BasicFilter<T>> {
        private Builder() {
            filter = new BasicFilter<T>();
        }

        public Builder<T> value(T value){
            filter.value = value;
            return this;
        }

        public Builder<T> operationType(OperationTypeHQL operationType){
            filter.operationType = operationType;
            return this;
        }

        @Override
        protected void validateFilter() {
            if(BusinessUtility.isAnyNull(filter.operationType, filter.value)) {
                throw new IllegalStateException("operationType and value fields must be set before building the " +
                        "BasicFilter object!");
            }
        }
    }

    private T value;
    private OperationTypeHQL operationType;

    public static <U extends Comparable<U>> Builder<U> builder(){
        return new Builder<U>();
    }

    private BasicFilter(){
        value = null;
        operationType = null;
    }

    private String getParameterNameForHQL(){
        return getFullFieldName(true) + "_" + id;
    }

    @Override
    public String toHQL() {
        return getFullFieldName(false) + operationType.getOperator() + ":" + getParameterNameForHQL();
    }

    @Override
    public Map<String, Object> getParameters() {
        return Map.of(getParameterNameForHQL(), value);
    }
}
