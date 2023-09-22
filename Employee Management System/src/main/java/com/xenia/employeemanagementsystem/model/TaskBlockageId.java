package com.xenia.employeemanagementsystem.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class TaskBlockageId implements Serializable {

    @Column(name = "BlockingTaskId")
    private int blockingTaskId;

    @Column(name = "BlockedTaskId")
    private int blockedTaskId;
}
