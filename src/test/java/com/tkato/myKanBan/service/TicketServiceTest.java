package com.tkato.myKanBan.service;

import com.tkato.myKanBan.exception.TicketNotFoundException;
import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.model.Ticket;
import com.tkato.myKanBan.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    private TicketService uut;

    @Mock private TicketRepository ticketRepository;

    @Mock private ProjectService projectService;

    private static final String TESTPID = "ABCD";

    private static final String TESTTID = "WXYZ";

    private static final String TESTUNAME = "johndoe";

    @BeforeEach
    void setUp() {
        uut = new TicketService(projectService, ticketRepository);
    }

    @Test
    void getAllTickets() {
        Project project = new Project();
        project.setProjectIdentifier(TESTPID);
        when(projectService.getProject(TESTPID, TESTUNAME)).thenReturn(project);

        uut.getAllTickets(TESTPID, TESTUNAME);
        verify(ticketRepository).findAllByProjectIdentifier(TESTPID);
    }

    @Test
    void getTicket() {
        Ticket ticket = new Ticket();
        ticket.setProjectIdentifier(TESTPID);
        when(ticketRepository.findByTicketIdentifier(TESTTID)).thenReturn(ticket);
        uut.getTicket(TESTPID, TESTTID, TESTUNAME);
    }

    @Test
    void getNonExistentTicketThrowsException() {
        assertThrows(TicketNotFoundException.class, () -> uut.getTicket(TESTPID, TESTTID, TESTUNAME));
    }

    @Test
//    @Disabled
    void addNewTicket() {
        Project project = new Project();
        project.setTicketCount(42);
        Ticket ticket = new Ticket();

        when(projectService.getProject(TESTPID, TESTUNAME)).thenReturn(project);
        Ticket added = uut.addNewTicket(ticket, TESTPID, TESTUNAME);
        verify(ticketRepository).save(ticket);
    }

    @Test
    void addExistingTicket() {
        Project project = new Project();
        project.setTicketCount(42);
        Ticket ticket = new Ticket();
        ticket.setId(1L); // Existing tickets will have an ID

        when(projectService.getProject(TESTPID, TESTUNAME)).thenReturn(project);
        Ticket existing = uut.addNewTicket(ticket, TESTPID, TESTUNAME);
        verify(ticketRepository).save(ticket);
    }

    @Test
    void deleteTicket() {
        TicketService spy = spy(uut);
        Ticket ticket = new Ticket();

        doReturn(ticket).when(spy).getTicket(TESTPID, TESTTID, TESTUNAME);
        spy.deleteTicket(TESTPID, TESTTID, TESTUNAME);
        verify(ticketRepository).deleteByTicketIdentifier(TESTTID);
    }
}