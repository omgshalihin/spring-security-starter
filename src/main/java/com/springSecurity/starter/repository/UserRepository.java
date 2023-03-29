package com.springSecurity.starter.repository;

import com.springSecurity.starter.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findUserByName(String username);
}
