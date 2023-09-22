package com.xenia.employeemanagementsystem.dto.request.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Getter
@Setter
public class TaskCreateRequest implements ICreateRequest{
    @NotNull @Positive private Integer departmentId;
    @NotBlank private String name;
    private String description;
    @NotNull private Byte priority;
    private Date dueDate;
}
