package com.xenia.employeemanagementsystem.dto.request.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.Optional;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentUpdateRequest implements IUpdateRequest{
    @NotNull @Positive private Integer id;
    private Optional<@NotBlank String> name;
    private Optional<@NotNull String> description;

    private Optional<Integer> managerId;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public void setDescription(String description) {
        this.description = Optional.ofNullable(description);
    }

    public void setManagerId(Integer managerId) {
        this.managerId = Optional.ofNullable(managerId);
    }
}
