package com.xenia.employeemanagementsystem.dto.response;

import com.xenia.employeemanagementsystem.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
@Builder
public class EmployeeResponse implements IEntityResponse{
    private int id;
    private String firstName;
    private String lastName;
    private Employee.Gender gender;
    private String email;
    private String phoneNumber;
    private String address;
    private BigDecimal salary;
    private Date hireDate;
    private Date leaveDate;
    private Integer supervisorId;
    private int departmentId;
    private Integer currentTaskId;

    public static final String HQL_CONSTRUCTOR_EXPRESSION = "SELECT NEW "
            + "com.xenia.employeemanagementsystem.dto.response.EmployeeResponse("
            + "id, firstName, lastName, gender, email, phoneNumber, address, salary, "
            + "hireDate, leaveDate, supervisor.id, department.id, currentTask.id) FROM Employee";
}
