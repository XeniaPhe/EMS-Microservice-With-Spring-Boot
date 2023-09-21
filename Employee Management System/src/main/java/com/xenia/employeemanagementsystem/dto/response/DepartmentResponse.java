package com.xenia.employeemanagementsystem.dto.response;

import com.xenia.employeemanagementsystem.dto.request.IEntityRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class DepartmentResponse implements IEntityResponse{
    private int id;
    private String name;
    private String description;
    private Integer managerId;

    public static final String HQL_CONSTRUCTOR_EXPRESSION = "SELECT NEW "
            + "com.xenia.employeemanagementsystem.dto.response.DepartmentResponse"
            + "(id, name, description, manager.id) FROM Department";
}
