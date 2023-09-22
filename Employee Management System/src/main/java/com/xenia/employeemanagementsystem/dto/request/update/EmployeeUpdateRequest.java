package com.xenia.employeemanagementsystem.dto.request.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xenia.employeemanagementsystem.model.Employee;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeUpdateRequest implements IUpdateRequest{
    @NotNull @Positive private Integer id;
    private Optional<@NotBlank String> firstName;
    private Optional<@NotBlank String> lastName;
    private Optional<Employee.Gender> gender;
    private Optional<@NotBlank String> email;
    private Optional<@NotBlank String> phoneNumber;
    private Optional<@NotBlank String> address;
    private Optional<@NotNull @Positive BigDecimal> salary;
    private Optional<@NotNull Date> leaveDate;
    private Optional<@Positive Integer> supervisorId;
    private Optional<@Positive Integer> currentTaskId;

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = Optional.ofNullable(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = Optional.ofNullable(lastName);
    }

    public void setGender(@NotNull Employee.Gender gender) {
        this.gender = Optional.of(gender);
    }

    public void setEmail(String email) {
        this.email = Optional.ofNullable(email);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Optional.ofNullable(phoneNumber);
    }

    public void setAddress(String address) {
        this.address = Optional.ofNullable(address);
    }

    public void setSalary(BigDecimal salary) {
        this.salary = Optional.ofNullable(salary);
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = Optional.ofNullable(leaveDate);
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = Optional.ofNullable(supervisorId);
    }

    public void setCurrentTaskId(Integer currentTaskId) {
        this.currentTaskId = Optional.ofNullable(currentTaskId);
    }
}
