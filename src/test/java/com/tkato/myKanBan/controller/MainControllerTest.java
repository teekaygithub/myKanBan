package com.tkato.myKanBan.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.tkato.myKanBan.model.Project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private MainController mockRepository;

    @Test
    public void addNewProjectTest() throws Exception {
        Project test = new Project();
        test.setTitle("testing");
        List<Project> projects = new ArrayList<>();
        projects.add(test);

        when(mockRepository.getAllProjects()).thenReturn(projects);

        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/api/all").toString(), String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        
    }
}
