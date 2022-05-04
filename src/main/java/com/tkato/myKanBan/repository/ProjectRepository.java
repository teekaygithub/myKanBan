package com.tkato.myKanBan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tkato.myKanBan.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    public Project findByProjectIdentifier(String projectIdentifier);

    public Project findByTitle(String title);

    public void deleteByProjectIdentifier(String projectIdentifier);

}