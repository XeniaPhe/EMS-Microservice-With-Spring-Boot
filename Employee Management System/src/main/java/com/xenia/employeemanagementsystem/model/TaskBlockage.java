package com.xenia.employeemanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TaskBlockage")
public class TaskBlockage implements IEntity{

    @EmbeddedId
    private TaskBlockageId id;

    @Column(name = "BlockageReason", columnDefinition = "VARCHAR(500)", nullable = false)
    private String blockageReason;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @MapsId("blockingTaskId")
    @JsonBackReference(value = "task-blockingtask")
    private Task blockingTask;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @MapsId("blockedTaskId")
    @JsonBackReference(value = "task-blockedtask")
    private Task blockedTask;
}
