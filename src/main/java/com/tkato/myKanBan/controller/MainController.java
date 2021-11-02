package com.tkato.myKanBan.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.model.Ticket;
import com.tkato.myKanBan.model.User;
import com.tkato.myKanBan.service.ProjectService;
import com.tkato.myKanBan.service.TicketService;
import com.tkato.myKanBan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/projects")
@CrossOrigin("http://localhost:3000")
@Validated
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    TicketService ticketService;

    // TODO: Get all tickets for a specific project

    // PROJECT ROUTES
    @GetMapping("/all")
    public List<Project> getAllProjects(Principal principal) {
        return userService.getAllProjects(principal.getName());
    }

    // TODO: Handle 'NoSuchElementException'
    // TODO: Integration test
    @GetMapping("/project/{id}")
    public Project getProject(@PathVariable Long id, Principal principal) {
        return projectService.getProject(id, principal.getName());
    }

    @PostMapping("/addproject")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProject(@Valid @RequestBody Project project, Principal principal) {
        projectService.addNewProject(project, principal.getName());
    }

    // TODO: Integration test
    @PutMapping("/project/{id}")
    public void modifyProject(@PathVariable Long id, @RequestBody Project project, Principal principal) {
        projectService.modifyProject(id, project, principal.getName());
    }

    // TODO: Integration test
    // TODO: Handle 'java.sql.SQLIntegrityConstraintViolationException'
    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable Long id, Principal principal) {
        projectService.deleteProjectById(id, principal.getName());
    }

    // TICKET ROUTES
    @GetMapping("/alltickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/project/ticket/{projectId}")
    public List<Ticket> getTicketByProject(@PathVariable Integer projectId) {
        return ticketService.getTicketByProjectId(projectId);
    }

    // TODO: Integration test
    @GetMapping("/ticket/{id}")
    public Ticket getTicket(@PathVariable Integer id) {
        return ticketService.getTicket(id);
    }

    @PostMapping("/addticket")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTicket(@RequestBody Ticket ticket) {
        ticketService.addNewTicket(ticket);
    }

    // TODO: Integration test
    @PutMapping("/ticket/{id}")
    public void modifyTicket(@PathVariable Integer id, @RequestBody Ticket ticket) {
        ticketService.modifyTicket(id, ticket);
    }

    // TODO: Integration test
    @DeleteMapping("/ticket/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }
}