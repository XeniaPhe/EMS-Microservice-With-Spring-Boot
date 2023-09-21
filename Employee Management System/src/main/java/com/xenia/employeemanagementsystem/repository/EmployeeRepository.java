package com.xenia.employeemanagementsystem.repository;

import com.xenia.employeemanagementsystem.dto.response.EmployeeResponse;
import com.xenia.employeemanagementsystem.filter.domain.EmployeeFilter;
import com.xenia.employeemanagementsystem.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("unchecked")
public class EmployeeRepository extends EntityRepository<Employee, EmployeeFilter, EmployeeResponse> {

    @Override
    protected Class<Employee> getEntityType() {
        return Employee.class;
    }
}
