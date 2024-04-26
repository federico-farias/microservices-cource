package com.bintics.todolist.repository;

import com.bintics.todolist.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoModel, String> {
}
