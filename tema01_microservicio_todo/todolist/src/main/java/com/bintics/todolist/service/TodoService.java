package com.bintics.todolist.service;

import com.bintics.todolist.dto.TodoListResponse;
import com.bintics.todolist.dto.TodoRequest;

import java.util.List;

public interface TodoService {
    String create(TodoRequest request);

    List<TodoListResponse> getAll();

    void update(TodoRequest request);

    void delete(String id);

}
