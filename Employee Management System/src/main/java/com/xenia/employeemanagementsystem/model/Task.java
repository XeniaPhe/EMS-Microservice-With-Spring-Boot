package com.xenia.employeemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xenia.employeemanagementsystem.exceptions.impl.InvalidValueException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
@Entity
@Table(name = "Task")
public class Task extends EntityBase{
    @Column(name = "Name", columnDefinition = "VARCHAR(75)", nullable = false)
    private String name;

    @Column(name = "Description", columnDefinition = "VARCHAR(500)")
    private String description;

    @Column(name = "Priority", columnDefinition = "TINYINT", nullable = false)
    private byte priority;

    @Setter(AccessLevel.NONE)
    @Column(name = "IssueDate", columnDefinition = "DATE")
    private Date issueDate;

    @Column(name = "DueDate", columnDefinition = "DATE")
    private Date dueDate;

    @Column(name = "TerminationDate", columnDefinition = "DATE")
    private Date terminationDate;

    @ManyToOne
    @JoinColumn(name = "DepartmentId")
    @JsonBackReference(value = "department-task")
    private Department department;

    public Task(){
        issueDate = new Date();
    }

    public void setPriority(byte priority){
        if(priority < 1 || priority > 10)
            throw new InvalidValueException("Priority must be within the range [1,10]");

        this.priority = priority;
    }
}
