package com.xenia.employeemanagementsystem.repository;

import com.xenia.employeemanagementsystem.dto.response.DepartmentResponse;
import com.xenia.employeemanagementsystem.filter.domain.DepartmentFilter;
import com.xenia.employeemanagementsystem.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository extends EntityRepository<Department, DepartmentFilter, DepartmentResponse> {

    @Override
    protected Class<Department> getEntityType() {
        return Department.class;
    }
}
