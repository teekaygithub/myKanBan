package com.tkato.myKanBan.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/accounts")
    public List<User> getUsers() {
        System.out.println("GET all accounts");
        return userService.getAllUsers();
    }

    @GetMapping("/test")
    public ResponseEntity<?> getTest() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        System.out.println("NOT IMPLEMENTED YET");
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
