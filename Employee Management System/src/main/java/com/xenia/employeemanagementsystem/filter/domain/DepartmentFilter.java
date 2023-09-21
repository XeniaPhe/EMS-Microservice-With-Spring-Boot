package com.xenia.employeemanagementsystem.filter.domain;

import com.xenia.employeemanagementsystem.dto.response.DepartmentResponse;
import com.xenia.employeemanagementsystem.filter.core.EntityFilter;
import com.xenia.employeemanagementsystem.filter.core.Filter;
import com.xenia.employeemanagementsystem.model.Department;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("unchecked")
public class DepartmentFilter extends EntityFilter<Department> {
    private Filter<String> nameFilter = null;
    private Filter<String> descriptionFilter = null;
    private EmployeeFilter managerFilter = null;

    @Override
    protected String getConstructorExpression() {
        return DepartmentResponse.HQL_CONSTRUCTOR_EXPRESSION;
    }

    public void setNameFilter(@NotNull Filter<String> nameFilter) {
        this.nameFilter = (Filter<String>) addFilter(this.nameFilter, nameFilter, "name");
    }

    public void setDescriptionFilter(@NotNull Filter<String> descriptionFilter) {
        this.nameFilter = (Filter<String>) addFilter(this.descriptionFilter, descriptionFilter, "description");
    }

    public void setManagerFilter(@NotNull EmployeeFilter managerFilter) {
        this.managerFilter = (EmployeeFilter) addFilter(this.managerFilter, managerFilter, "manager");
    }
}
