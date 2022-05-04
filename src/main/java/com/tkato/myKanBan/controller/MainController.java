package com.tkato.myKanBan.controller;

import java.security.Principal;
import java.util.Set;

import javax.validation.Valid;

import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.model.Ticket;
import com.tkato.myKanBan.service.ProjectService;
import com.tkato.myKanBan.service.TicketService;
import com.tkato.myKanBan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/projects")
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
    public ResponseEntity<Set<Project>> getAllProjects(Principal principal) {
        return ResponseEntity.ok().body(userService.getAllProjects(principal.getName()));
    }

    // TODO: Integration test
    @GetMapping("/project")
    public ResponseEntity<Project> getProject(@RequestParam String projectIdentifier, Principal principal) {
        Project project = projectService.getProject(projectIdentifier, principal.getName());
        return ResponseEntity.ok().body(project);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Cannot find project or ticket");
    }

    @PostMapping("/addproject")
    public ResponseEntity<?> addProject(@Valid @RequestBody Project project, Principal principal) {
        Project newProject = projectService.addNewProject(project, principal.getName());
        return new ResponseEntity<Project>(newProject, HttpStatus.CREATED);
    }

    // TODO: Integration test
    @PutMapping("/project")
    public ResponseEntity<Project> modifyProject(@Valid @RequestBody Project project, Principal principal) {
        Project modified = projectService.modifyProject(project, principal.getName());
        return ResponseEntity.ok().body(modified);
    }

    // TODO: Integration test
    // TODO: Handle 'java.sql.SQLIntegrityConstraintViolationException'
    @DeleteMapping("/project")
    public ResponseEntity<?> deleteProject(@RequestParam String projectIdentifier, Principal principal) {
        projectService.deleteProjectById(projectIdentifier, principal.getName());
        return new ResponseEntity<String>(String.format("Project with PID %s successfully deleted", projectIdentifier), HttpStatus.OK);
    }

    // TICKET ROUTES
    @GetMapping("/alltickets")
    public ResponseEntity<Set<Ticket>> getAllTickets(@RequestParam String projectIdentifier, Principal principal) {
        Set<Ticket> tickets = ticketService.getAllTickets(projectIdentifier, principal.getName());
        return ResponseEntity.ok().body(tickets);
    }

    // TODO: Integration test
    @GetMapping("/ticket")
    public ResponseEntity<Ticket> getTicket(@RequestParam String projectIdentifier, @RequestParam String ticketIdentifier, Principal principal) {
        Ticket ticket = ticketService.getTicket(projectIdentifier, ticketIdentifier, principal.getName());
        return ResponseEntity.ok().body(ticket);
    }

    @PostMapping("/ticket")
    public ResponseEntity<Ticket> addNewTicket(@RequestParam String projectIdentifier, @Valid @RequestBody Ticket ticket, Principal principal) {
        Ticket newTicket = ticketService.addNewTicket(ticket, projectIdentifier, principal.getName());
        return new ResponseEntity<Ticket>(newTicket, HttpStatus.CREATED);
    }

    // TODO: Integration test
    @DeleteMapping("/ticket")
    public ResponseEntity<String> deleteTicket(@RequestParam String projectIdentifier, @RequestParam String ticketIdentifier, Principal principal) {
        ticketService.deleteTicket(projectIdentifier, ticketIdentifier, principal.getName());
        return new ResponseEntity<String>(String.format("Successfully deleted ticket %s", ticketIdentifier), HttpStatus.OK);
    }
}