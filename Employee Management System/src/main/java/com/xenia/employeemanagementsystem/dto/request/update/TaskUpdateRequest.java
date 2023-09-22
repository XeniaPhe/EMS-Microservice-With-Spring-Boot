package com.xenia.employeemanagementsystem.dto.request.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.Optional;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskUpdateRequest implements IUpdateRequest{
    @NotNull @Positive private Integer id;
    private Optional<@NotBlank String> name;
    private Optional<@NotBlank String> description;
    private Optional<@NotNull Byte> priority;
    private Optional<@NotNull Date> dueDate;
    private Optional<@NotNull Date> terminationDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public void setDescription(String description) {
        this.description = Optional.ofNullable(description);
    }

    public void setPriority(byte priority) {
        this.priority = Optional.of(priority);
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = Optional.ofNullable(dueDate);
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = Optional.ofNullable(terminationDate);
    }
}
