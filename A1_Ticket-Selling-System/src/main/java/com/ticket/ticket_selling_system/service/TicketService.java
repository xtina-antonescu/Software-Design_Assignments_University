package com.ticket.ticket_selling_system.service;

import com.ticket.ticket_selling_system.model.Show;
import com.ticket.ticket_selling_system.model.Ticket;
import com.ticket.ticket_selling_system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
    }

    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicket(Integer id){
        return ticketRepository.findById(id).get();
    }

    public void delete(Integer id){
        ticketRepository.deleteById(id);
    }

    public Ticket modifyNumberOfSeats(Integer id, Integer numberSeats){
        ticketRepository.findById(id).map(ticket-> {
            ticket.setNumberOfSeats(numberSeats);
            return ticketRepository.save(ticket);
        });
        return null;
    }

    public Ticket modifyTicketName(Integer id, String name){
        ticketRepository.findById(id).map(ticket-> {
            ticket.setTicketName(name);
            return ticketRepository.save(ticket);
        });
        return null;
    }

    public Ticket modifyShow(Integer id, Show show){
        ticketRepository.findById(id).map(ticket-> {
            ticket.setShow(show);
            return ticketRepository.save(ticket);
        });
        return null;
    }

}
