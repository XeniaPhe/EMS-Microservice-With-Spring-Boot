package com.xenia.employeemanagementsystem.filter.criteria.domain;

import com.xenia.employeemanagementsystem.filter.criteria.core.Criteria;
import com.xenia.employeemanagementsystem.filter.criteria.core.enums.OperationTypeURI;
import com.xenia.employeemanagementsystem.filter.domain.DepartmentFilter;
import com.xenia.employeemanagementsystem.filter.domain.TaskFilter;
import com.xenia.employeemanagementsystem.model.Task;
import com.xenia.employeemanagementsystem.utility.CriteriaUtility;

import java.util.Date;

public class TaskCriteria extends EntityCriteria<Task>{
    private Criteria<String> nameCriteria;
    private Criteria<Byte> priorityCriteria;
    private Criteria<Date> issueDateCriteria;
    private Criteria<Date> dueDateCriteria;
    private Criteria<Date> terminationDateCriteria;
    private Criteria<Integer> departmentIdCriteria;


    public TaskCriteria(Integer id, String name, Byte priority, Byte priorityUpper, OperationTypeURI priorityOp,
                        Date issueDate, Date issueDateAfter, OperationTypeURI issueDateOp,
                        Date dueDate, Date dueDateAfter, OperationTypeURI dueDateOp,
                        Date terminationDate, Date terminationDateAfter, OperationTypeURI terminationDateOp,Integer departmentId){

        super(id);
        this.nameCriteria = CriteriaUtility.getCriteriaOf(name);

        this.priorityCriteria = CriteriaUtility.getCriteriaOf(priority, priorityUpper, priorityOp);
        this.issueDateCriteria = CriteriaUtility.getCriteriaOf(issueDate, issueDateAfter, issueDateOp);
        this.dueDateCriteria = CriteriaUtility.getCriteriaOf(dueDate, dueDateAfter, dueDateOp);
        this.terminationDateCriteria = CriteriaUtility.getCriteriaOf(terminationDate, terminationDateAfter, terminationDateOp);
        this.departmentIdCriteria = CriteriaUtility.getCriteriaOf(departmentId);
    }


    @Override
    public boolean isAllCriteriaEmpty() {
        return CriteriaUtility.isAllCriteriaEmpty(idCriteria, nameCriteria, priorityCriteria, issueDateCriteria,
                dueDateCriteria, terminationDateCriteria, departmentIdCriteria);
    }

    @Override
    public TaskFilter buildFilter() {
        TaskFilter filter = new TaskFilter();

        if(!idCriteria.isEmpty())
            filter.setIdFilter(idCriteria.buildFilter());
        if(!nameCriteria.isEmpty())
            filter.setNameFilter(nameCriteria.buildFilter());
        if(!priorityCriteria.isEmpty())
            filter.setPriorityFilter(priorityCriteria.buildFilter());
        if(!issueDateCriteria.isEmpty())
            filter.setIssueDateFilter(issueDateCriteria.buildFilter());
        if(!dueDateCriteria.isEmpty())
            filter.setDueDateFilter(dueDateCriteria.buildFilter());
        if(!terminationDateCriteria.isEmpty())
            filter.setTerminationDateFilter(terminationDateCriteria.buildFilter());

        if(!departmentIdCriteria.isEmpty()){
            DepartmentFilter departmentFilter = new DepartmentFilter();
            departmentFilter.setIdFilter(departmentIdCriteria.buildFilter());
            filter.setDepartmentFilter(departmentFilter);
        }

        return filter;
    }
}
