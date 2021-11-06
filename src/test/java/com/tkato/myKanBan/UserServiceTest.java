package com.tkato.myKanBan;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @BeforeEach
    public void setup() {
        this.bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        this.userRepository = mock(UserRepository.class);
        this.projectRepository = mock(ProjectRepository.class);
        this.userService = new UserService(bCryptPasswordEncoder, userRepository, projectRepository);
    }

    @Test
    public void getOneUserTest() {
        when(userRepository.findByUsername(any())).thenReturn(new User());
        User user = userService.getUser("testuser");

        assertNotNull(user);
        assertTrue(user.isEnabled());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isCredentialsNonExpired());
    }
}
