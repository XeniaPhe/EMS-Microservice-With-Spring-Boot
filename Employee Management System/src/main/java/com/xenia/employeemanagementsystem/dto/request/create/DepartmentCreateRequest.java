package com.xenia.employeemanagementsystem.dto.request.create;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class DepartmentCreateRequest implements ICreateRequest {
    @NotBlank private String name;
    private String description;
    @Positive private Integer managerId;
}
