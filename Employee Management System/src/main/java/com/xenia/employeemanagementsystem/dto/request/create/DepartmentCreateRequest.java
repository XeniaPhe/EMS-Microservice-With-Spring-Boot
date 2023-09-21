package com.xenia.employeemanagementsystem.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentCreateRequest implements ICreateRequest {
    private String name;
    private String description;
    private Integer managerId;
}
