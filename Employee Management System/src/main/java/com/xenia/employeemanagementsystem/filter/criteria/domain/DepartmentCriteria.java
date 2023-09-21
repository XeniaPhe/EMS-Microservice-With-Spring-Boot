package com.xenia.employeemanagementsystem.filter.criteria.domain;

import com.xenia.employeemanagementsystem.filter.criteria.core.Criteria;
import com.xenia.employeemanagementsystem.filter.domain.DepartmentFilter;
import com.xenia.employeemanagementsystem.filter.domain.EmployeeFilter;
import com.xenia.employeemanagementsystem.model.Department;
import com.xenia.employeemanagementsystem.utility.CriteriaUtility;

public class DepartmentCriteria extends EntityCriteria<Department>{
    private Criteria<String> nameCriteria;
    private Criteria<Integer> managerIdCriteria;

    public DepartmentCriteria(Integer id, String name, Integer managerId){
        super(id);
        this.nameCriteria = CriteriaUtility.getCriteriaOf(name);
        this.managerIdCriteria = CriteriaUtility.getCriteriaOf(managerId);
    }


    @Override
    public boolean isAllCriteriaEmpty() {
        return CriteriaUtility.isAllCriteriaEmpty(idCriteria, nameCriteria, managerIdCriteria);
    }

    @Override
    public DepartmentFilter buildFilter() {
        DepartmentFilter filter = new DepartmentFilter();

        if(!idCriteria.isEmpty())
            filter.setIdFilter(idCriteria.buildFilter());
        if(!nameCriteria.isEmpty())
            filter.setNameFilter(nameCriteria.buildFilter());

        if(!managerIdCriteria.isEmpty()){
            EmployeeFilter managerFilter = new EmployeeFilter();
            managerFilter.setIdFilter(managerIdCriteria.buildFilter());
            filter.setManagerFilter(managerFilter);
        }

        return filter;
    }
}
