package com.tkato.myKanBan.repository;

import java.util.Set;

import com.tkato.myKanBan.model.Ticket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    
    public Set<Ticket> findAllByProjectIdentifier(String projectIdentifier);

    public Ticket findByTicketIdentifier(String ticketIdentifier);

    public void deleteByTicketIdentifier(String ticketIdentifier);
}