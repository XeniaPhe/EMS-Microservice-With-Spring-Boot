package com.xenia.employeemanagementsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TaskBlockageId implements Serializable {

    @Column(name = "BlockingTaskId")
    private int blockingTaskId;

    @Column(name = "BlockedTaskId")
    private int blockedTaskId;
}
