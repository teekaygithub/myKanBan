package com.tkato.myKanBan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.repository.ProjectRepository;

@Service
public class ProjectService {
    
    @Autowired
    ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll().forEach(pj -> projects.add(pj));
        return projects;
    }

    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).get();
    }

    public void addNewProject(Project project) {
        projectRepository.save(project);
    }

    public void modifyProject(Integer id, Project project) {
        Project temp = getProjectById(id);
        temp.setTitle(project.getTitle());
        temp.setDescription(project.getDescription());
        projectRepository.save(temp);
    }

    public void deleteProjectById(Integer id) {
        projectRepository.deleteById(id);
    }
}
