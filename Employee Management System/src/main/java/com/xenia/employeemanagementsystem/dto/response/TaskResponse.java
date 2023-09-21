package com.xenia.employeemanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Builder
public class TaskResponse implements IEntityResponse{
    private int id;
    private String name;
    private String description;
    private byte priority;
    private Date issueDate;
    private Date dueDate;
    private Date terminationDate;
    private int departmentId;

    public static final String HQL_CONSTRUCTOR_EXPRESSION = "SELECT NEW "
            + "com.xenia.employeemanagementsystem.dto.response.TaskResponse"
            + "(id, name, description, priority, issueDate, dueDate, terminationDate, department.id) FROM Task";
}
