package com.tkato.myKanBan.controller;

import java.util.List;

import javax.validation.Valid;

import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.model.Ticket;
import com.tkato.myKanBan.service.ProjectService;
import com.tkato.myKanBan.service.TicketService;

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
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
@Validated
public class MainController {

    @Autowired
    ProjectService projectService;

    @Autowired
    TicketService ticketService;

    // TODO: Get all tickets for a specific project

    // PROJECT ROUTES
    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // TODO: Handle 'NoSuchElementException'
    // TODO: Integration test
    @GetMapping("/project/{id}")
    public Project getProject(@PathVariable Integer id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("/addproject")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProject(@Valid @RequestBody Project project) {
        projectService.addNewProject(project);
    }

    // TODO: Integration test
    @PutMapping("/project/{id}")
    public void modifyProject(@PathVariable Integer id, @RequestBody Project project) {
        projectService.modifyProject(id, project);
    }

    // TODO: Integration test
    // TODO: Handle 'java.sql.SQLIntegrityConstraintViolationException'
    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable Integer id) {
        projectService.deleteProjectById(id);
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