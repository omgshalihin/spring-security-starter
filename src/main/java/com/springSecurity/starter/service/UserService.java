package com.springSecurity.starter.service;

import com.springSecurity.starter.model.UserModel;
import com.springSecurity.starter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public String createUser(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRoles(userModel.getPassword().toUpperCase());
        userRepository.save(userModel);
        return String.format("User [%s] has been added to the database", userModel.getUserName());
    }


}
