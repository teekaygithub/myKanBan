package com.tkato.myKanBan.repository;

import com.tkato.myKanBan.model.Ticket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {}