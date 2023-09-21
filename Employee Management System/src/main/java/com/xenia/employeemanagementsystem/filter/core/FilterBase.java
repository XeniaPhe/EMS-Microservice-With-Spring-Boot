package com.xenia.employeemanagementsystem.filter.core;

import java.util.Map;

public abstract class FilterBase {
    FilterBase(){}

    protected abstract void setRoot(String root);

    public abstract String toHQL();
    public abstract Map<String, Object> getParameters();
}
