package com.example.springex.todo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document("todos")
public class Todo {
    @Id
    private String id;
    @NotNull(message = "The title is required")
    @Size(min = 3 ,message = "Title must be at least 3 characters long")
    private String title;
    @NotNull(message = "Description is required")
    private String description;
    private Long timestamp;

    public Todo(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timestamp = System.currentTimeMillis();
    }

    public Todo() {
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
