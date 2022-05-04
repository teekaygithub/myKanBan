package com.tkato.myKanBan.controller;

import java.util.Set;

import javax.validation.Valid;

import com.tkato.myKanBan.model.User;
import com.tkato.myKanBan.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<Set<User>> getUsers() {
        Set<User> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/")
    public ResponseEntity<User> getUser(@RequestParam String username ) {
        User user = userService.getUser(username);
        return ResponseEntity.ok().body(user);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<?> modifyUser(@Valid @RequestBody User existing) {
        User user = userService.updateUser(existing);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteUser(@RequestParam String username) {
        userService.deleteUser(username);
        return new ResponseEntity<String>(String.format("%s successfully deleted", username), HttpStatus.OK);
    }
}
