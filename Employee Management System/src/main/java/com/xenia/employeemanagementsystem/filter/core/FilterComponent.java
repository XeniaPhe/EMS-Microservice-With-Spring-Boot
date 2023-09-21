package com.xenia.employeemanagementsystem.filter.core;

import com.xenia.employeemanagementsystem.filter.core.enums.LogicalOperator;
import com.xenia.employeemanagementsystem.utility.BusinessUtility;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class FilterComponent<T extends Comparable<T>>{
    public static class Builder<T extends Comparable<T>>{
        private FilterComponent<T> component;

        public Builder(){
            component = new FilterComponent<>();
        }

        public Builder<T> logicalOperator(LogicalOperator logicalOperator){
            component.logicalOperator = logicalOperator;
            return this;
        }

        public Builder<T> filter(Filter<T> filter){
            component.filter = filter;
            return this;
        }

        public FilterComponent<T> build(){
            validateComponent();
            return component;
        }

        private void validateComponent(){
            if(BusinessUtility.isAnyNull(component.logicalOperator, component.filter)){
                throw new IllegalStateException("filter and logicalOperator fields must be set before " +
                        "building the FilterComponent object!");
            }
        }
    }

    @Setter(AccessLevel.PACKAGE)
    private LogicalOperator logicalOperator;
    private Filter<T> filter;

    public static <U extends Comparable<U>> Builder<U> builder(){
        return new Builder<>();
    }

    private FilterComponent(){
        logicalOperator = null;
        filter = null;
    }
}
