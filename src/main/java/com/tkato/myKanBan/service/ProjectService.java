package com.tkato.myKanBan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.tkato.myKanBan.exception.ProjectNotFoundException;
import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.model.User;
import com.tkato.myKanBan.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(pj -> projects.add(pj));
        return projects;
    }

    public Project getProject(Long id, String username) {
        User user = (User)userService.loadUserByUsername(username);
        Project project =  projectRepository.findById(id);

        if (project == null) {
            throw new ProjectNotFoundException(String.format("Could not find project with ID %d", id));
        }
        
        if (user.getProject().contains(project)) {
            return project;
        } else {
            // Define custom exception for project not found
            // throw new Exception("");
            throw new ProjectNotFoundException("Project not found in your account");
        }
    }

    public void addNewProject(Project project, String username) {
        User user = (User)userService.loadUserByUsername(username);
        project.addUser(user);
        user.addProject(project);
        projectRepository.save(project);
    }

    public void modifyProject(Long id, Project project, String username) {
        Project temp = getProject(id, username);
        temp.setTitle(project.getTitle());
        temp.setDescription(project.getDescription());
        projectRepository.save(temp);
    }

    public void deleteProjectById(Long id, String username) {
        Project project = getProject(id, username);
        projectRepository.deleteById(project.getId());
    }
}
