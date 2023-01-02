package com.tkato.myKanBan.service;

import com.tkato.myKanBan.exception.TicketNotFoundException;
import com.tkato.myKanBan.model.Project;
import com.tkato.myKanBan.model.Ticket;
import com.tkato.myKanBan.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        this.uut = new TicketService(projectService, ticketRepository);
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
    @Disabled
    void addNewTicket() {
    }

    @Test
    @Disabled
    void deleteTicket() {
    }
}