package com.xenia.employeemanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Department")
public class Department extends EntityBase {

    @Column(name = "Name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(name = "Description", columnDefinition = "VARCHAR(500)")
    private String description;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ManagerId")
    private Employee manager;
}
