package com.xenia.employeemanagementsystem.filter.domain;

import com.xenia.employeemanagementsystem.dto.response.TaskResponse;
import com.xenia.employeemanagementsystem.filter.core.EntityFilter;
import com.xenia.employeemanagementsystem.filter.core.Filter;
import com.xenia.employeemanagementsystem.model.Task;

import javax.validation.constraints.NotNull;
import java.util.Date;

@SuppressWarnings("unchecked")
public class TaskFilter extends EntityFilter<Task> {
    private Filter<String> nameFilter = null;
    private Filter<String> descriptionFilter = null;
    private Filter<Byte> priorityFilter = null;
    private Filter<Date> issueDateFilter = null;
    private Filter<Date> dueDateFilter = null;
    private Filter<Date> terminationDateFilter = null;
    private DepartmentFilter departmentFilter = new DepartmentFilter();

    protected String getConstructorExpression() {
        return TaskResponse.HQL_CONSTRUCTOR_EXPRESSION;
    }

    public void setNameFilter(@NotNull Filter<String> nameFilter) {
        this.nameFilter = (Filter<String>) addFilter(this.nameFilter, nameFilter, "name");
    }

    public void setDescriptionFilter(@NotNull Filter<String> descriptionFilter) {
        this.descriptionFilter = (Filter<String>) addFilter(this.descriptionFilter, descriptionFilter, "description");
    }

    public void setPriorityFilter(@NotNull Filter<Byte> priorityFilter) {
        this.priorityFilter = (Filter<Byte>) addFilter(this.priorityFilter, priorityFilter, "priority");
    }

    public void setIssueDateFilter(@NotNull Filter<Date> issueDateFilter) {
        this.issueDateFilter = (Filter<Date>) addFilter(this.issueDateFilter, issueDateFilter, "issueDate");
    }

    public void setDueDateFilter(@NotNull Filter<Date> dueDateFilter) {
        this.dueDateFilter = (Filter<Date>) addFilter(this.dueDateFilter, dueDateFilter, "dueDate");
    }

    public void setTerminationDateFilter(@NotNull Filter<Date> terminationDateFilter) {
        this.terminationDateFilter = (Filter<Date>) addFilter(this.terminationDateFilter, terminationDateFilter, "terminationDate");
    }

    public void setDepartmentFilter(@NotNull DepartmentFilter departmentFilter) {
        this.departmentFilter = (DepartmentFilter) addFilter(this.departmentFilter, departmentFilter, "department");
    }
}
