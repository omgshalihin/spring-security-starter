package com.springSecurity.starter.controller;

import com.springSecurity.starter.model.UserModel;
import com.springSecurity.starter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/new")
    public String createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }
}
