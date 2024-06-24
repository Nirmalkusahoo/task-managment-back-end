package com.pesto.task_management.rto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * TaskRto is a Request Transfer Object (RTO) class that represents a task.
 * It includes fields for task name, description, and status.
 * It also includes getter and setter methods for these fields.
 * The status field is validated to be not null and not empty.
 */
public class TaskRto {
    private String name;
    private String description;

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
