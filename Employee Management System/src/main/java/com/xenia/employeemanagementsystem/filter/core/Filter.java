package com.xenia.employeemanagementsystem.filter.core;

import java.util.Map;

public abstract class Filter<T extends Comparable<T>> extends FilterBase {
    protected static int counter = 0;

    protected int id;
    private String root;
    private String fieldName;

    protected Filter(){
        this.id = ++counter;
        this.fieldName = null;
        this.root = null;
    }

    @Override
    protected void setRoot(String root){
        this.root = root;
    }

    void setFieldName(String fieldName){
        this.fieldName = fieldName;
    }

    protected String getFullFieldName(boolean parameterName){
        if(root == null)
            return fieldName;

        return root + (parameterName ? "_" : ".") + fieldName;
    }

    @Override
    public boolean equals(Object other){
        if(other == null)
            return false;

        if(!(other instanceof Filter))
            return false;

        Filter<?> otherFilter = (Filter<?>)other;

        return otherFilter.id == this.id;
    }

    @Override
    public int hashCode(){
        return Integer.hashCode(id);
    }
}
