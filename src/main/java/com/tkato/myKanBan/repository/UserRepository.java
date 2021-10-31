package com.tkato.myKanBan.repository;

import com.tkato.myKanBan.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
