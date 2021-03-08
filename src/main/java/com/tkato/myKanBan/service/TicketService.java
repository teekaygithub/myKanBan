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
}