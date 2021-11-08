package com.tkato.myKanBan.service;

import java.util.Set;

import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.model.Ticket;
import com.tkato.myKanBan.repository.TicketRepository;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private ProjectService projectService;

    private TicketRepository ticketRepository;

    public TicketService(ProjectService projectService, TicketRepository ticketRepository) {
        this.projectService = projectService;
        this.ticketRepository = ticketRepository;
    }

    public Set<Ticket> getAllTickets(String PID, String username) {
        Project project = projectService.getProject(PID, username);
        Set<Ticket> tickets = ticketRepository.findAllByProjectIdentifier(project.getProjectIdentifier());
        return tickets;
    }

    public Ticket getTicket(String PID, String TID, String username) {
        // Make sure both the project and the user actually exists
        // If project doesn't exist or the user does not have permission to the project, an exception will be thrown
        projectService.getProject(PID, username);

        Ticket ticket = ticketRepository.findByTicketIdentifier(TID);
        // if (!ticket.getProjectIdentifier().equals(PID)) {
        //     // TODO: implement exception
        //     throw new TicketNotFoundException();
        // }
        return ticket;
    }

    // public Set<Ticket> getTicketByProjectId(Integer projectId) {
    //     return ticketRepository.findByProjectId(projectId);
    // }

    public Ticket addNewTicket(Ticket ticket, String projectIdentifier, String username) {
        Project project = projectService.getProject(projectIdentifier, username);

        // Is this an existing ticket? If so update
        if (ticket.getId() != null) {
            return ticketRepository.save(ticket);
        } else {
            // Brand new ticket, assign a ticket ID and save
            Integer ticketNumber = project.getTicketCount();
            ticket.setProject(project);
            ticket.setTicketIdentifier(project.getProjectIdentifier() + "-" + Integer.toString(ticketNumber + 1));
            ticket.setProjectIdentifier(projectIdentifier);
            project.setTicketCount(ticketNumber + 1);
            return ticketRepository.save(ticket);
        }        
    }

    // public ResponseEntity<String> deleteTicket(Integer id) {
    //     ticketRepository.deleteById(id);
    //     return new ResponseEntity<String>(String.format("Successfully deleted ticket ID %d", id), HttpStatus.OK);
    // }
}