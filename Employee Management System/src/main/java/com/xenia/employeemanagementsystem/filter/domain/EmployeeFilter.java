package com.xenia.employeemanagementsystem.filter.domain;

import com.xenia.employeemanagementsystem.dto.response.EmployeeResponse;
import com.xenia.employeemanagementsystem.filter.core.EntityFilter;
import com.xenia.employeemanagementsystem.filter.core.Filter;
import com.xenia.employeemanagementsystem.model.Employee;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("unchecked")
public class EmployeeFilter extends EntityFilter<Employee> {
    private Filter<String> firstNameFilter = null;
    private Filter<String> lastNameFilter = null;
    private Filter<Employee.Gender> genderFilter = null;
    private Filter<String> emailFilter = null;
    private Filter<String> phoneNumberFilter = null;
    private Filter<String> addressFilter = null;
    private Filter<BigDecimal> salaryFilter = null;
    private Filter<Date> hireDateFilter = null;
    private Filter<Date> leaveDateFilter = null;

    private EmployeeFilter supervisorFilter = null;
    private DepartmentFilter departmentFilter = null;
    private TaskFilter currentTaskFilter = null;

    public EmployeeFilter(){}

    protected String getConstructorExpression(){
        return EmployeeResponse.HQL_CONSTRUCTOR_EXPRESSION;
    }

    public void setFirstNameFilter(@NotNull Filter<String> firstNameFilter) {
        this.firstNameFilter = (Filter<String>) addFilter(this.firstNameFilter, firstNameFilter, "firstName");
    }

    public void setLastNameFilter(@NotNull Filter<String> lastNameFilter) {
        this.lastNameFilter = (Filter<String>) addFilter(this.lastNameFilter, lastNameFilter, "lastName");
    }

    public void setGenderFilter(@NotNull Filter<Employee.Gender> genderFilter) {
        this.genderFilter = (Filter<Employee.Gender>) addFilter(this.genderFilter, genderFilter, "gender");
    }

    public void setEmailFilter(@NotNull Filter<String> emailFilter) {
        this.emailFilter = (Filter<String>) addFilter(this.emailFilter, emailFilter, "email");
    }

    public void setPhoneNumberFilter(@NotNull Filter<String> phoneNumberFilter) {
        this.phoneNumberFilter = (Filter<String>) addFilter(this.phoneNumberFilter,phoneNumberFilter, "phoneNumber");
    }

    public void setAddressFilter(@NotNull Filter<String> addressFilter) {
        this.addressFilter = (Filter<String>) addFilter(this.addressFilter, addressFilter, "address");
    }

    public void setSalaryFilter(@NotNull Filter<BigDecimal> salaryFilter) {
        this.salaryFilter = (Filter<BigDecimal>) addFilter(this.salaryFilter, salaryFilter, "salary");
    }

    public void setHireDateFilter(@NotNull Filter<Date> hireDateFilter) {
        this.hireDateFilter = (Filter<Date>) addFilter(this.hireDateFilter, hireDateFilter, "hireDate");
    }

    public void setLeaveDateFilter(@NotNull Filter<Date> leaveDateFilter) {
        this.leaveDateFilter = (Filter<Date>) addFilter(this.leaveDateFilter, leaveDateFilter, "leaveDate");
    }

    public void setSupervisorFilter(@NotNull EmployeeFilter supervisorFilter) {
        this.supervisorFilter = (EmployeeFilter) addFilter(this.supervisorFilter, supervisorFilter, "supervisor");
    }

    public void setDepartmentFilter(@NotNull DepartmentFilter departmentFilter) {
        this.departmentFilter = (DepartmentFilter) addFilter(this.departmentFilter, departmentFilter, "department");
    }

    public void setCurrentTaskFilter(@NotNull TaskFilter currentTaskFilter) {
        this.currentTaskFilter = (TaskFilter) addFilter(this.currentTaskFilter, currentTaskFilter, "currentTask");
    }
}
