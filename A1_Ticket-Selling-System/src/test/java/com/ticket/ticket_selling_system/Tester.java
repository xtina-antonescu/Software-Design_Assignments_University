package com.ticket.ticket_selling_system;

import com.ticket.ticket_selling_system.controller.AdminController;
import com.ticket.ticket_selling_system.controller.CashierController;
import com.ticket.ticket_selling_system.model.Show;
import com.ticket.ticket_selling_system.model.Ticket;
import com.ticket.ticket_selling_system.repository.ShowRepository;
import com.ticket.ticket_selling_system.repository.TicketRepository;
import com.ticket.ticket_selling_system.service.ShowService;
import com.ticket.ticket_selling_system.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.mockito.Mockito.mock;

public class Tester {
    AdminController adminController = new AdminController();
    CashierController cashierController = new CashierController();

    @Test
    public void modifyTicketTest() {
        Show show = new Show("Balkan Remix", "Florin Salam", "Manele", "05/04.2023", "20:00", 3, 0);
        TicketRepository ticketRepository = mock(TicketRepository.class);
        TicketService ticketService = new TicketService(ticketRepository);
        ShowRepository showRepository = mock(ShowRepository.class);
        ShowService showService = new ShowService(showRepository);
        showService.saveShow(show);
        Ticket ticket = new Ticket(3, "ABC", show);
        ticketService.saveTicket(ticket);

        cashierController.setTicketService(ticketService);
        cashierController.setShowService(showService);

        if(cashierController.modifyTicketSeats(1,100).equals("Maximum capacity for seats is exceeded!")){
            System.out.println("Modify ticket test is ok!");
        }
    }
}
