package com.xenia.employeemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="Employee")
public class Employee extends EntityBase{
    public enum Gender {
        MALE,
        FEMALE
    }

    @Column(name = "FirstName", columnDefinition = "VARCHAR(30)", nullable = false)
    private String firstName;

    @Column(name = "LastName", columnDefinition = "VARCHAR(30)", nullable = false)
    private String lastName;

    @Column(name = "Gender", columnDefinition = "VARCHAR(6)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "Email", columnDefinition = "VARCHAR(60)",nullable = false)
    private String email;

    @Column(name = "PhoneNumber", columnDefinition = "VARCHAR(11)", nullable = false)
    private String phoneNumber;

    @Column(name = "Address", columnDefinition = "VARCHAR(150)", nullable = false)
    private String address;

    @Column(name = "Salary", columnDefinition = "DECIMAL(8,2)", nullable = false)
    private BigDecimal salary;

    @Setter(AccessLevel.NONE)
    @Column(name = "HireDate", columnDefinition = "DATE")
    private Date hireDate;
    
    @Column(name = "LeaveDate", columnDefinition = "DATE")
    private Date leaveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SupervisorId")
    @JsonBackReference(value = "employee-employee")
    private Employee supervisor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentId")
    @JsonBackReference(value = "department-employee")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CurrentTaskId")
    @JsonBackReference(value = "employee-task")
    private Task currentTask;

    public Employee(){
        hireDate = new Date();
    }
}
