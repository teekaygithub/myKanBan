package com.tkato.myKanBan.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@SpringBootTest(/*webEnvironment = WebEnvironment.RANDOM_PORT*/)
@TestPropertySource(
    locations = "classpath:application-test.properties"
)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class MainControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void registerUserTest() throws Exception {
        String username = "batman@test.com";
        String fullname = "Bruce Wayne";
        String password = "martha";
        String body = "{\"username\":\"" + username + "\", \"fullname\":\"" + fullname + "\", \"password\":\"" + password + "\"}";

        MvcResult result = mvc.perform(post("http://localhost:8080/api/users/register").content(body).contentType("application/json"))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals(result.getResponse().getStatus(), HttpStatus.CREATED.value());
    }

    // @Test
    // public void getAllUsersTest() throws Exception {
    //     MvcResult result = mvc.perform(get("http://localhost:8080/api/users/accounts")).andExpect(status().isOk()).andReturn();

    //     String resposne = result.getResponse().getContentAsString();

    //     assertEquals(1, 1);
    // }

    // @Test
    // @WithMockUser("/someguy")
    // public void loginTest() throws Exception {
    //     String username = "batman@test.com";
    //     String password = "martha";
    //     String body = "{\"username\":\"" + username + "\", \"password\":\""
    //               + password + "\"}";
    //     MvcResult result = mvc.perform(post("http://localhost:8080/api/users/login").content(body).contentType("application/json"))
    //             .andExpect(status().isOk())
    //             .andReturn();

    //     String response = result.getResponse().getContentAsString();

    //     // assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
    //     assertEquals(1, 1);
    // }

    /*@Test
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
	}*/

    // @Test
    // public void getAllProjectsTest() {
    //     ResponseEntity<List<Project>> res = restTemplate.exchange(
    //         "http://localhost:" + port + "/api/all", 
    //         HttpMethod.GET, 
    //         null, 
    //         new ParameterizedTypeReference<List<Project>>(){}
    //     );

    //     assertEquals(HttpStatus.OK, res.getStatusCode());
    // }

    // @Test
    // public void getSingleProjectTest() {
    //     // First login and generate token
    //     String username = "batman@test.com";
    //     String pw = "martha";

    //     ResponseEntity<String> auth = restTemplate.postForEntity(
    //         "http://localhost:" + port + "/api/users/login", 
    //         "{\"username\":\"" + username + "\", \"password\":\"" + pw + "\"}", 
    //         String.class
    //     );

    //     String token = auth.getBody();
    //     System.out.println(token);
        
    //     // ResponseEntity<Project> res = restTemplate.getForEntity(
    //     //     "http://localhost:" + port + "/api/projects/project?projectIdentifier=TEST", 
    //     //     Project.class
    //     // );

    //     // assertEquals(HttpStatus.FORBIDDEN, res.getStatusCode());
    //     assertEquals(1, 1);
    // }

    // @Test
    // public void addNewTicketTest() {
    //     // First get project ID
    //     ResponseEntity<List<Project>> res = restTemplate.exchange(
    //         "http://localhost:" + port + "/api/all", 
    //         HttpMethod.GET, 
    //         null, 
    //         new ParameterizedTypeReference<List<Project>>(){}
    //     );

    //     final Integer projectId = res.getBody().get(0).getId();

    //     // Build new ticket request
    //     Ticket test = new Ticket();
    //     test.setTitle("test ticket");
    //     test.setProjectId(projectId);
    //     test.setStatus("TODO");

    //     ResponseEntity<String> response = restTemplate.postForEntity(
    //         "http://localhost:" + port + "/api/addticket", test, String.class
    //     );

    //     assertEquals(HttpStatus.CREATED, response.getStatusCode());
    // }

    // @Test
    // public void getAllTicketsTest() {
    //     ResponseEntity<List<Ticket>> res = restTemplate.exchange(
    //         "http://localhost:" + port + "/api/alltickets",
    //         HttpMethod.GET,
    //         null,
    //         new ParameterizedTypeReference<List<Ticket>>(){}
    //     );

    //     assertEquals(HttpStatus.OK, res.getStatusCode());
    // }
}
