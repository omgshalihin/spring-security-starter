package com.springSecurity.starter.repository;

import com.springSecurity.starter.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
