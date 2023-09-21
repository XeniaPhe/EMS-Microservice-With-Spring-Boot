package com.xenia.employeemanagementsystem.filter.criteria.domain;

import com.xenia.employeemanagementsystem.filter.core.EntityFilter;
import com.xenia.employeemanagementsystem.filter.criteria.core.Criteria;
import com.xenia.employeemanagementsystem.model.EntityBase;
import com.xenia.employeemanagementsystem.utility.CriteriaUtility;

public abstract class EntityCriteria<T extends EntityBase> {

    protected Criteria<Integer> idCriteria;

    protected EntityCriteria(Integer id){
        this.idCriteria = CriteriaUtility.getCriteriaOf(id);
    }

    public abstract boolean isAllCriteriaEmpty();
    public abstract EntityFilter<T> buildFilter();
}
