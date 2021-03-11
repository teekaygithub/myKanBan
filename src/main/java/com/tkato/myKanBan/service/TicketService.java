package com.tkato.myKanBan.service;

import java.util.ArrayList;
import java.util.List;

import com.tkato.myKanBan.model.Ticket;
import com.tkato.myKanBan.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(ticket -> tickets.add(ticket));
        return tickets;
    }

    public Ticket getTicket(Integer id) {
        return ticketRepository.findById(id).get();
    }

    public void addNewTicket(Ticket ticket) {
        System.out.format("ticket's project ID: %d\n", ticket.getProjectId());
        ticketRepository.save(ticket);
    }

    public void modifyTicket(Integer id, Ticket ticket) {
        Ticket temp = ticketRepository.findById(id).get();
        temp.setTitle(ticket.getTitle());
        temp.setDescription(ticket.getDescription());
        temp.setStatus(ticket.getStatus().toString());
        temp.setProjectId(ticket.getProjectId());
        ticketRepository.save(temp);
    }
}