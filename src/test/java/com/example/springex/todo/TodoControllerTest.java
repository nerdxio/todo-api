package com.example.springex.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    public void whenGetAllTodos_ReturnJsonArray() throws Exception {
        Todo todo1 = new Todo("1", "Todo1", "Todo1");
        Todo todo2 = new Todo("2", "Todo2", "Todo2");
        List<Todo> data = Arrays.asList(todo1, todo2);
        given(todoService.getAllTodo()).willReturn(data);

        mockMvc.perform(
                        get("/api/v1/todos")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", equalTo(todo1.getTitle())));
    }

    @Test
    public void whenPostTodo_ThenCreateTodo()  throws Exception{
        Todo todo1 = new Todo("1", "Todo1", "Todo1");
        given(todoService.save(Mockito.any(Todo.class))).willReturn(todo1);
        ObjectMapper objectMap= new ObjectMapper();
        objectMap.writeValueAsString(todo1);
        mockMvc.perform(post("/api/v1/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( objectMap.writeValueAsString(todo1))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(todo1.getTitle())));
    }

}
