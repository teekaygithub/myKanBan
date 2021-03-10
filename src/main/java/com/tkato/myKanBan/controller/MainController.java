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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    // TODO: Get a specific project
    // TODO: Update a project
    // TODO: Delete a project
    // TODO: Get a specific ticket
    // TODO: Add a new ticket for a specific project
    // TODO: Update a ticket
    // TODO: Delete a ticket

    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping("/addproject")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProject(@Valid @RequestBody Project project) {
        projectService.addNewProject(project);
    }

    @GetMapping("/alltickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PostMapping("/addticket")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTicket(@RequestBody Ticket ticket) {
        ticketService.addNewTicket(ticket);
    }

    // For testing only
    @GetMapping("/")
    public String testGet() {
        return "GET request complete";
    }
}