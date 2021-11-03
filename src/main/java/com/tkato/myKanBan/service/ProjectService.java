package com.tkato.myKanBan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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

    public Set<Project> getAllProjects() {
        Set<Project> projects = new HashSet<>();
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

        // Do not add the project to the account if it already exists
        if (existing != null && userInProject(existing, username)) {
            throw new ProjectAlreadyExists("Project already exists in your account");
        }
        
        User user = (User)userService.loadUserByUsername(username);
        project.addUser(user);
        user.addProject(project);
        project.setProjectIdentifier(PID);
        projectRepository.save(project);
    }

    public void modifyProject(Project project, String username) {
        Project existing = getProject(project.getProjectIdentifier(), username);
        existing.setTitle(project.getTitle());
        existing.setDescription(project.getDescription());
        existing.setTarget_date(project.getTarget_date());
        projectRepository.save(existing);
    }

    public void deleteProjectById(String PID, String username) {
        Project project = getProject(PID, username);
        for (User user : project.getUser()) {
            user.getProject().remove(project);
        }
        projectRepository.deleteById(project.getId());
    }

    private boolean userInProject(Project project, String username) {
        Set<User> userlist = project.getUser();
        for (User u : userlist) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }
}
