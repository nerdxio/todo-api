package com.example.springex.todo;

import com.example.springex.error.ConflictException;
import com.example.springex.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    private final TodosRepository todosRepository;

    @Autowired
    public TodoService(TodosRepository todosRepository) {
        this.todosRepository = todosRepository;
    }

    public List<Todo> getAllTodo() {
        return todosRepository.findAll();
    }

    public Todo getById(String id) {

        try {
            return todosRepository.findById(id).get();
        } catch (NoSuchElementException exception) {
            throw new NotFoundException(String.format("No record with this id [%s] was found in our database", id));
        }
    }

    public Todo save(Todo todo) {
        if (todosRepository.findByTitle(todo.getTitle()) != null) {
            throw new ConflictException("Anther record with the same title");
        }
        return todosRepository.insert(todo);
    }

    public void delete(String id) {
        todosRepository.deleteById(id);
    }
}
