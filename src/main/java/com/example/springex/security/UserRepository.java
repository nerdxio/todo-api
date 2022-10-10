package com.example.springex.security;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<AppUser,String> {
  AppUser findAppUserByName(String username);
}
