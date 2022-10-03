package com.example.springex.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodosRepository todosRepository;

    public List<Todo> getAllTodo() {
        return todosRepository.findAll();
    }

    public Todo getById(String id) {

        return todosRepository.findById(id).get();
    }

    public Todo save(Todo todo) {
        return todosRepository.insert(todo);
    }

    public void delete(String id) {
       todosRepository.deleteById(id);
    }
}
