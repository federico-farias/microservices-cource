package com.bintics.todolist.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TodoListResponse {
    private String id;

    private String name;

    private Boolean done;

    private Date createdAt;

    private Date updatedAt;
}
