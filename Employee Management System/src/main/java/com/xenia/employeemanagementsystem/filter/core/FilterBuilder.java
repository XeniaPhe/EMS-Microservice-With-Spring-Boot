package com.xenia.employeemanagementsystem.filter.core;

abstract class FilterBuilder<T extends Filter> {
    protected T filter;
    protected abstract void validateFilter();

    public T build(){
        validateFilter();
        return filter;
    }
}
