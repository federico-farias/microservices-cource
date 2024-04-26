package com.bintics.todolist.dto;

import lombok.Data;

@Data
public class TodoRequest {

    private String id;

    private String name;

    private Boolean done;

}
