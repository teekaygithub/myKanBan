package com.tkato.myKanBan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import com.tkato.myKanBan.model.User;
import com.tkato.myKanBan.repository.ProjectRepository;
import com.tkato.myKanBan.repository.UserRepository;
import com.tkato.myKanBan.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceTest {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private UserService userService;
    private Set<User> userList;

    @BeforeEach
    public void setup() {
        // Set up userservice and its dependencies
        this.bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        this.userRepository = mock(UserRepository.class);
        this.projectRepository = mock(ProjectRepository.class);
        this.userService = new UserService(bCryptPasswordEncoder, userRepository, projectRepository);

        // Dummy data
        this.userList = new HashSet<>();
        userList.add(new User("batman@test.com", "Bruce Wayne", "martha"));
        userList.add(new User("tstark@test.com", "Tony Stark", "iamironman"));
    }

    @Test
    public void getOneUserTest() {
        User existing = new User();
        existing.setUsername("testuser");

        when(userRepository.findByUsername(any())).thenReturn(existing);
        User user = userService.getUser("testuser");

        assertNotNull(user);
        assertTrue(user.isEnabled());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(this.userList);
        Set<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(users.size(), this.userList.size());
    }

    @Test
    public void saveUserTest() {
        User newuser = new User();
        newuser.setUsername("testuser");
        newuser.setPassword("iloveunitttesting");

        when(userRepository.findByUsername(any())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(newuser);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("encodedpw");
        
        User saved = userService.saveUser(newuser);

        assertNotNull(saved);
        assertEquals(saved.getUsername(), "testuser");
        assertEquals(saved.getPassword(), "encodedpw");
        assertTrue(saved.isEnabled());
    }
}
