package com.xenia.employeemanagementsystem.filter.criteria.domain;

import com.xenia.employeemanagementsystem.filter.criteria.core.Criteria;
import com.xenia.employeemanagementsystem.filter.criteria.core.enums.OperationTypeURI;
import com.xenia.employeemanagementsystem.filter.domain.DepartmentFilter;
import com.xenia.employeemanagementsystem.filter.domain.EmployeeFilter;
import com.xenia.employeemanagementsystem.filter.domain.TaskFilter;
import com.xenia.employeemanagementsystem.model.Employee;
import com.xenia.employeemanagementsystem.utility.CriteriaUtility;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeCriteria extends EntityCriteria<Employee>{
    private Criteria<String> firstNameCriteria;
    private Criteria<String> lastNameCriteria;
    private Criteria<Employee.Gender> genderCriteria;
    private Criteria<BigDecimal> salaryCriteria;
    private Criteria<Date> hireDateCriteria;
    private Criteria<Date> leaveDateCriteria;

    private Criteria<Integer> departmentIdCriteria;

    private Criteria<Integer> supervisorIdCriteria;
    private Criteria<Integer> currentTaskIdCriteria;


    public EmployeeCriteria(Integer id, String firstName, String lastName, Employee.Gender gender,
                            BigDecimal salary, BigDecimal salaryUpper, OperationTypeURI salaryOp,
                            Date hireDate, Date hireDateAfter, OperationTypeURI hireDateOp,
                            Date leaveDate, Date leaveDateAfter, OperationTypeURI leaveDateOp,
                            Integer departmentId, Integer supervisorId, Integer currentTaskId){

        super(id);
        this.firstNameCriteria = CriteriaUtility.getCriteriaOf(firstName);
        this.lastNameCriteria = CriteriaUtility.getCriteriaOf(lastName);
        this.genderCriteria = CriteriaUtility.getCriteriaOf(gender);

        this.salaryCriteria = CriteriaUtility.getCriteriaOf(salary, salaryUpper, salaryOp);
        this.hireDateCriteria = CriteriaUtility.getCriteriaOf(hireDate, hireDateAfter, hireDateOp);
        this.leaveDateCriteria = CriteriaUtility.getCriteriaOf(leaveDate, leaveDateAfter, leaveDateOp);

        this.departmentIdCriteria = CriteriaUtility.getCriteriaOf(departmentId);
        this.supervisorIdCriteria = CriteriaUtility.getCriteriaOf(supervisorId);
        this.currentTaskIdCriteria = CriteriaUtility.getCriteriaOf(currentTaskId);
    }

    @Override
    public boolean isAllCriteriaEmpty(){
        return CriteriaUtility.isAllCriteriaEmpty(idCriteria, firstNameCriteria, lastNameCriteria, genderCriteria, salaryCriteria,
                hireDateCriteria, leaveDateCriteria, departmentIdCriteria, supervisorIdCriteria, currentTaskIdCriteria);
    }

    @Override
    public EmployeeFilter buildFilter() {
        EmployeeFilter filter = new EmployeeFilter();

        if(!idCriteria.isEmpty())
            filter.setIdFilter(idCriteria.buildFilter());
        if(!firstNameCriteria.isEmpty())
            filter.setFirstNameFilter(firstNameCriteria.buildFilter());
        if(!lastNameCriteria.isEmpty())
            filter.setLastNameFilter(lastNameCriteria.buildFilter());
        if(!genderCriteria.isEmpty())
            filter.setGenderFilter(genderCriteria.buildFilter());
        if(!salaryCriteria.isEmpty())
            filter.setSalaryFilter(salaryCriteria.buildFilter());
        if(!hireDateCriteria.isEmpty())
            filter.setHireDateFilter(hireDateCriteria.buildFilter());
        if(!leaveDateCriteria.isEmpty())
            filter.setLeaveDateFilter(leaveDateCriteria.buildFilter());

        if(!departmentIdCriteria.isEmpty()){
            DepartmentFilter departmentFilter = new DepartmentFilter();
            departmentFilter.setIdFilter(departmentIdCriteria.buildFilter());
            filter.setDepartmentFilter(departmentFilter);
        }

        if(!supervisorIdCriteria.isEmpty()){
            EmployeeFilter supervisorFilter = new EmployeeFilter();
            supervisorFilter.setIdFilter(supervisorIdCriteria.buildFilter());
            filter.setSupervisorFilter(supervisorFilter);
        }

        if(!currentTaskIdCriteria.isEmpty()){
            TaskFilter taskFilter = new TaskFilter();
            taskFilter.setIdFilter(currentTaskIdCriteria.buildFilter());
            filter.setCurrentTaskFilter(taskFilter);
        }

        return filter;
    }
}
