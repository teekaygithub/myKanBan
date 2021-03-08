package com.tkato.myKanBan.controller;

import java.util.List;

import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class MainController {

    @Autowired
    ProjectService projectService;
    
    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/")
    public String testGet() {
        return "GET request complete";
    }
}