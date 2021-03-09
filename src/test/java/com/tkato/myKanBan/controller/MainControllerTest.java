package com.tkato.myKanBan.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import com.tkato.myKanBan.model.Project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(
    locations = "classpath:application-test.properties"
)
public class MainControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addNewProjectTest() {
        Project test = new Project();
        test.setTitle("test project");

        ResponseEntity<String> res = restTemplate.postForEntity(
            "http://localhost:" + port + "/api/addproject", test, String.class
        );

        assertEquals(HttpStatus.CREATED, res.getStatusCode());
    }

    @Test
    public void addNewProjectBadRequest() throws Exception {
        Project test = new Project();

        ResponseEntity<String> res = restTemplate.postForEntity(
            "http://localhost:" + port + "/api/addproject", test, String.class);
        
        assertNotEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
	}

    @Test
    public void getAllProjectsTest() {
        ResponseEntity<List<Project>> res = restTemplate.exchange(
            "http://localhost:" + port + "/api/all", 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<List<Project>>(){}
        );

        assertEquals(HttpStatus.OK, res.getStatusCode());
        System.out.format("len: %d\n", res.getBody().size());
    }
}
