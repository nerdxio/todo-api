package com.example.springex.todo;
import com.example.springex.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @MockBean
    private TodosRepository todosRepository;
    @Test
    public void whenFindAll_ReturnTodoList() {
        Todo todo1 = new Todo("1","Todo1","Todo1");
        Todo todo2 = new Todo("2","Todo2","Todo2");
        List<Todo> data = Arrays.asList(todo1,todo2);
        given(todosRepository.findAll()).willReturn(data);

        assertThat(todoService.getAllTodo()).hasSize(2).contains(todo1,todo2);
    }

    @Test
    public void whenGetById_TodoShouldFound(){
        Todo todo = new Todo("1","Todo1","Todo1");
        given(todosRepository.findById(anyString())).willReturn(Optional.ofNullable(todo));

        Todo result =todoService.getById("1");
        assertThat(result.getTitle()).containsIgnoringCase("todo");
    }

}
