package com.tkato.myKanBan.controller;

import java.util.List;

import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.model.Ticket;
import com.tkato.myKanBan.service.ProjectService;
import com.tkato.myKanBan.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class MainController {

    @Autowired
    ProjectService projectService;

    @Autowired
    TicketService ticketService;
    
    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping("/addProject")
    public void addProject(@RequestBody Project project) {
        System.out.println(project);
        projectService.addNewProject(project);
    }

    @GetMapping("/alltickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/")
    public String testGet() {
        return "GET request complete";
    }
}