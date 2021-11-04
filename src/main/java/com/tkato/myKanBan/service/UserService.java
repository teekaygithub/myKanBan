package com.tkato.myKanBan.service;

import java.util.HashSet;
import java.util.Set;

import com.tkato.myKanBan.exception.UserAlreadyExistsException;
import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.model.User;
import com.tkato.myKanBan.repository.ProjectRepository;
import com.tkato.myKanBan.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(u -> users.add(u));
        return users;
    }

    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
        return user;
    }

    public User saveUser(User newUser) {
        User user = userRepository.findByUsername(newUser.getUsername());
        if (user != null) {
            throw new UserAlreadyExistsException("User already exists in database");
        }
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public User updateUser(User existing) {
        User user = getUser(existing.getUsername()); // If user not found, 'getUser' will throw an exception
        existing.setPassword(user.getPassword());
        return userRepository.save(existing);
    }

    public Set<Project> getAllProjects(String username) {
        User user = (User)loadUserByUsername(username);
        return user.getProject();
    }

    public void deleteUser(String username) {
        User user = getUser(username);

        // Remove user from all the projects they have access to
        for (Project p: user.getProject()) {
            if (p.getUser().size() == 1) {
                projectRepository.deleteByProjectIdentifier(p.getProjectIdentifier());
            } else {
                p.getUser().remove(user);
            }
        }

        // Remove user
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        return user;
    }
}
