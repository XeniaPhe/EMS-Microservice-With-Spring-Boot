package com.xenia.employeemanagementsystem.dto.request.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

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
