package com.xenia.employeemanagementsystem.dto.request.create;

import com.xenia.employeemanagementsystem.model.Employee;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class EmployeeCreateRequest implements ICreateRequest {
    @NotNull @Positive private Integer departmentId;
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @NotNull private Employee.Gender gender;
    private String email;
    @NotBlank private String phoneNumber;
    private String address;
    @NotNull @Positive private BigDecimal salary;
    @Positive private Integer supervisorId;
}
