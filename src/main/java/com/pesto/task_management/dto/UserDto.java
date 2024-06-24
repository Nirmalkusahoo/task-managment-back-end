package com.pesto.task_management.dto;

import javax.persistence.*;

/**
 * UserDto is a Data Transfer Object (DTO) class that represents a user.
 * It is annotated with JPA annotations to map it to a database table.
 * It includes fields for user id, username, and password.
 * It also includes getter and setter methods for these fields.
 */
@Entity
@Table
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
