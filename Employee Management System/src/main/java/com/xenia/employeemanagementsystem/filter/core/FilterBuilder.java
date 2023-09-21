package com.xenia.employeemanagementsystem.filter.core;

abstract class FilterBuilder<T extends Filter> {
    protected T filter;
    public abstract T build();
}
