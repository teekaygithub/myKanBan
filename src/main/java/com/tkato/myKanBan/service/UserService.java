package com.tkato.myKanBan.service;

import java.util.List;

import com.tkato.myKanBan.model.User;
import com.tkato.myKanBan.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>)userRepository.findAll();
    }
}
