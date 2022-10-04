package com.example.springex.todo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodosRepository extends MongoRepository<Todo,String>{

    Todo findByTitle(String title);
}
