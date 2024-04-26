package com.bintics.todolist.service;

import com.bintics.todolist.dto.TodoListResponse;
import com.bintics.todolist.dto.TodoRequest;
import com.bintics.todolist.model.TodoModel;
import com.bintics.todolist.repository.TodoListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoListRepository repository;

    @Override
    public String create(TodoRequest request) {
        var id = UUID.randomUUID().toString();
        var now = LocalDateTime.now();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        TodoModel model = new TodoModel(id, request.getName(), false, date, date);
        this.repository.save(model);
        return id;
    }

    @Override
    public List<TodoListResponse> getAll() {
        var listResponse = this.repository.findAll().stream()
                .map(m -> new TodoListResponse(
                        m.getId(),
                        m.getName(),
                        m.getDone(),
                        m.getCreatedAt(),
                        m.getUpdatedAt()))
                .collect(Collectors.toList());
        return listResponse;
    }

    @Override
    public void update(TodoRequest request) {
        var model = this.repository.findById(request.getId()).orElseThrow();
        var now = LocalDateTime.now();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        model.setName(request.getName());
        model.setDone(request.getDone() == null ? false : request.getDone());
        model.setUpdatedAt(date);
        this.repository.save(model);
    }

    @Override
    public void delete(String id) {
        var todo = this.repository.findById(id).orElseThrow();
        this.repository.delete(todo);
    }

}
