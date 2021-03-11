package com.tkato.myKanBan.repository;

import java.util.List;

import com.tkato.myKanBan.model.Ticket;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    @Query(value="SELECT * FROM ticket WHERE projectId=?1")
    List<Ticket> findByProjectId(Integer id);
}