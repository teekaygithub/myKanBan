package com.tkato.myKanBan.controller;

import java.util.ArrayList;
import java.util.List;

import com.tkato.myKanBan.model.User;
import com.tkato.myKanBan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/accounts")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    // TODO: POST route for logging in
    // @PostMapping("/login")
    // public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
    //     return new ResponseEntity<Object>
    // }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        System.out.println("NOT IMPLEMENTED YET");
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
