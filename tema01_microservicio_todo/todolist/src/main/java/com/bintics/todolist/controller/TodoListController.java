package com.bintics.todolist.controller;

import com.bintics.todolist.dto.TodoRequest;
import com.bintics.todolist.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/todo-list")
public class TodoListController {

    private final TodoService service;

    @PostMapping
    public ResponseEntity create(@RequestBody TodoRequest request) {
        var id = this.service.create(request);
        return ResponseEntity.created(URI.create(String.format("/todo-list/%s", id))).build();
    }

    @GetMapping
    public ResponseEntity getAll() {
        var list = this.service.getAll();
        return ResponseEntity.ok(list);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody TodoRequest request) {
        request.setId(id);
        this.service.update(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
