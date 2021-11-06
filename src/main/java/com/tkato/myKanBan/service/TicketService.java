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

        Integer ticketNumber = project.getTicketCount();
        ticket.setProject(project);
        ticket.setTicketIdentifier(project.getProjectIdentifier() + "-" + Integer.toString(ticketNumber + 1));
        ticket.setProjectIdentifier(projectIdentifier);
        project.setTicketCount(ticketNumber + 1);
        return ticketRepository.save(ticket);
    }

    // public void modifyTicket(Integer id, Ticket ticket) {
    //     Ticket temp = ticketRepository.findById(id).get();
    //     temp.setTitle(ticket.getTitle());
    //     temp.setDescription(ticket.getDescription());
    //     temp.setStatus(ticket.getStatus().toString());
    //     // temp.setProjectId(ticket.getProjectId());
    //     ticketRepository.save(temp);
    // }

    // public ResponseEntity<String> deleteTicket(Integer id) {
    //     ticketRepository.deleteById(id);
    //     return new ResponseEntity<String>(String.format("Successfully deleted ticket ID %d", id), HttpStatus.OK);
    // }
}