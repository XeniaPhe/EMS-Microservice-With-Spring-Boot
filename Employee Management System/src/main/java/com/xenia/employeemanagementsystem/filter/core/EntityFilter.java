package com.xenia.employeemanagementsystem.filter.core;

import com.xenia.employeemanagementsystem.exceptions.impl.IllegalOperationException;
import com.xenia.employeemanagementsystem.model.EntityBase;
import com.xenia.employeemanagementsystem.utility.BusinessUtility;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class EntityFilter<T extends EntityBase> extends FilterBase {
    protected Filter<Integer> idFilter = null;
    private List<FilterBase> filtersProvided = new ArrayList<>();
    private String root = null;
    private boolean isOriginFilter = true;

    public EntityFilter(){

    }

    public void setIdFilter(@NotNull Filter<Integer> idFilter) {
        this.idFilter = (Filter<Integer>) addFilter(this.idFilter, idFilter, "id");
    }

    protected FilterBase addFilter(Filter<?> currentFilter, Filter<?> newFilter, String fieldName){
        newFilter.setFieldName(fieldName);

        if(!BusinessUtility.isNullOrEmpty(root))
            newFilter.setRoot(root);

        return addToFilterList(currentFilter, newFilter);
    }

    protected FilterBase addFilter(EntityFilter currentFilter, EntityFilter newFilter, String addedRoot){
        if(newFilter.isEmpty())
            throw new IllegalOperationException("Attempted to assign an empty filter!");

        String filterRoot = BusinessUtility.isNullOrEmpty(root) ? addedRoot : root + "." + addedRoot;

        newFilter.setRoot(filterRoot);
        newFilter.isOriginFilter = false;

        return addToFilterList(currentFilter, newFilter);
    }

    private FilterBase addToFilterList(FilterBase currentFilter, FilterBase newFilter){
        if(filtersProvided.contains(currentFilter))
            filtersProvided.remove(currentFilter);

        filtersProvided.add(newFilter);
        return newFilter;
    }

    private boolean isEmpty(){
        return filtersProvided.isEmpty();
    }

    @Override
    protected void setRoot(String root){
        this.root = root;

        for(FilterBase filter : filtersProvided){
            filter.setRoot(root);
        }
    }

    protected abstract String getConstructorExpression();

    @Override
    public String toHQL(){
        StringBuilder hqlBuilder = new StringBuilder();

        if(isOriginFilter){
            hqlBuilder.append(getConstructorExpression());

            if(isEmpty())
                return hqlBuilder.toString();

            hqlBuilder.append(" WHERE ");
        }

        hqlBuilder.append("(");

        for(FilterBase filter : filtersProvided){
            hqlBuilder.append(filter.toHQL()).append(" AND ");
        }

        //delete the last AND operation
        int len = hqlBuilder.length();
        hqlBuilder.delete(len - 5, len);
        hqlBuilder.append(')');

        return hqlBuilder.toString();
    }

    @Override
    public Map<String, Object> getParameters(){
        Map<String, Object> parameters = new HashMap<>();
        for(FilterBase filter : filtersProvided){
            parameters.putAll(filter.getParameters());
        }

        return parameters;
    }
}