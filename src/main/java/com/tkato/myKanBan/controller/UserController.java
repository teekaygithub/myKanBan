package com.tkato.myKanBan.controller;

import java.util.Set;

import javax.validation.Valid;

import com.tkato.myKanBan.model.User;
import com.tkato.myKanBan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/accounts")
    public Set<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/")
    public User getUser(@RequestParam String username ) {
        // TODO: custom exception for user not found
        User user = userService.getUser(username);
        return user;
    }

    @GetMapping("/test")
    public ResponseEntity<?> getTest() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
