package com.xenia.employeemanagementsystem.filter.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompositeFilter<T extends Comparable<T>> extends Filter<T>{
    public static class Builder<T extends Comparable<T>> extends FilterBuilder<CompositeFilter<T>> {
        private Builder() {
            filter = new CompositeFilter<T>();
        }

        public Builder<T> addComponent(FilterComponent<T> component) {
            if (filter.components.size() == 0)
                component.setLogicalOperator(null);

            filter.components.add(component);
            return this;
        }

        public CompositeFilter<T> build() {
            validateFilter();
            return filter;
        }

        private void validateFilter() {
            if (filter.getParameters().size() == 0)
                throw new IllegalStateException("CompositeFilter must at least have one FilterComponent!");
        }
    }

    private List<FilterComponent<T>> components;

    public static <U extends Comparable<U>> Builder<U> builder(){
        return new Builder<U>();
    }

    private CompositeFilter(){
        components = new ArrayList<FilterComponent<T>>();
    }

    @Override
    protected void setRoot(String root){
        super.setRoot(root);

        for(FilterComponent<T> component : components){
            component.getFilter().setRoot(root);
        }
    }

    @Override
    void setFieldName(String fieldName){
        super.setFieldName(fieldName);

        for(FilterComponent<T> component : components){
            component.getFilter().setFieldName(fieldName);
        }
    }

    @Override
    public String toHQL() {
        StringBuilder hqlBuilder = new StringBuilder("(");

        for(FilterComponent<T> component : components){
            if(component.getLogicalOperator() != null){
                hqlBuilder.append(' ').append(component.getLogicalOperator().name()).append(' ');
            }

            hqlBuilder.append(component.getFilter().toHQL());
        }

        return hqlBuilder.append(')').toString();
    }

    @Override
    public Map<String, Object> getParameters() {
        Map<String, Object> params = new HashMap<>();

        for (FilterComponent<T> component : components){
            params.putAll(component.getFilter().getParameters());
        }

        return params;
    }
}
