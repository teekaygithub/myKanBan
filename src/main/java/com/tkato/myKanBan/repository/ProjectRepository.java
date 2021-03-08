package com.tkato.myKanBan.repository;

import org.springframework.data.repository.CrudRepository;
import com.tkato.myKanBan.model.*;

public interface ProjectRepository extends CrudRepository<Project, Long> {

}