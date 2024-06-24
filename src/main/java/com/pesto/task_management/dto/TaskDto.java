package com.pesto.task_management.dto;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * TaskDto is a Data Transfer Object (DTO) class that represents a task.
 * It is annotated with JPA annotations to map it to a database table.
 * It includes fields for task id, name, description, and status.
 * It also includes getter and setter methods for these fields.
 */
@Entity
@Table()
public class TaskDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Column
    private String name;

    @Column
    private String description;

    @Column
    @NotNull
    @NotEmpty
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
