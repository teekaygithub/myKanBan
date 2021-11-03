package com.tkato.myKanBan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.tkato.myKanBan.exception.ProjectAlreadyExists;
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

    public Project getProject(String PID, String username) {
        User user = (User)userService.loadUserByUsername(username);
        Project project =  projectRepository.findByProjectIdentifier(PID);

        if (project == null) {
            throw new ProjectNotFoundException(String.format("Could not find project with PID %s", PID));
        }
        
        if (user.getProject().contains(project)) {
            return project;
        } else {
            throw new ProjectNotFoundException("Project not found in your account");
        }
    }

    public void addNewProject(Project project, String username) {
        String PID = project.getProjectIdentifier().toUpperCase();
        Project existing = projectRepository.findByProjectIdentifier(PID);
        User user = (User)userService.loadUserByUsername(username);

        // Do not add the project to the account if it already exists
        if (existing != null && userInProject(existing, username)) {
            throw new ProjectAlreadyExists("Project already exists in your account");
        }
        
        project.addUser(user);
        user.addProject(project);
        project.setProjectIdentifier(PID);
        projectRepository.save(project);
    }

    public void modifyProject(Project project, String username) {
        Project temp = getProject(project.getProjectIdentifier(), username);
        temp.setTitle(project.getTitle());
        temp.setDescription(project.getDescription());
        projectRepository.save(temp);
    }

    public void deleteProjectById(String PID, String username) {
        Project project = getProject(PID, username);
        for (User user : project.getUser()) {
            user.getProject().remove(project);
        }
        projectRepository.deleteById(project.getId());
    }

    private boolean userInProject(Project project, String username) {
        List<User> userlist = project.getUser();
        for (User u : userlist) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }
}
