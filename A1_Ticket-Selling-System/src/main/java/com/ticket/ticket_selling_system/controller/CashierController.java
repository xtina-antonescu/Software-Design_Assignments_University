package com.ticket.ticket_selling_system.controller;

import com.ticket.ticket_selling_system.model.Cashier;
import com.ticket.ticket_selling_system.model.Show;
import com.ticket.ticket_selling_system.model.Ticket;
import com.ticket.ticket_selling_system.service.CashierService;
import com.ticket.ticket_selling_system.service.ShowService;
import com.ticket.ticket_selling_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cashier")
@CrossOrigin
public class CashierController {

    @Autowired
    private CashierService cashierService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ShowService showService;

    public CashierService getCashierService() {
        return cashierService;
    }

    public void setCashierService(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public ShowService getShowService() {
        return showService;
    }

    public void setShowService(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping("/add")
    public String add(@RequestBody Cashier cashier){
        cashierService.saveCashier(cashier);
        return "New cashier is added";
    }

    @GetMapping("/getAll")
    public List<Cashier> list(){
        return cashierService.getAllCashiers();
    }

    @GetMapping("/log-in/{username}/{password}")
    public String logIn(@PathVariable String username, @PathVariable String password){
        if(cashierService.getCashier(username,password) != null){
            return "Cashier logged-in successfully!";
        }

        return "Could not log-in!";
    }

    @DeleteMapping("/logged-in/ticket/delete/{id}")
    public String deleteTicket(@PathVariable Integer id){
        ticketService.delete(id);
        return "Ticket deleted successfully!";
    }

    @GetMapping("/logged-in/ticket/get/{id}")
    public Ticket getTicket(@PathVariable Integer id){
        return ticketService.getTicket(id);
    }

    @PutMapping("/logged-in/ticket/modify-number-seats/{id}/{numberSeats}")
    public String modifyTicketSeats(@PathVariable Integer id, @PathVariable Integer numberSeats){
        Ticket ticket= ticketService.getTicket(id);
        if(numberSeats > ticket.getNumberOfSeats()) {
            if (!showService.canSellTickets(ticket.getShow().getShowID(), numberSeats - ticket.getNumberOfSeats())) {
                return "Maximum capacity for seats is exceeded!";
            }
        }

        if(showService.modifyTicketsSold(ticket.getShow().getShowID(), -ticket.getNumberOfSeats()) == null){
            return "Could not modify ticket seats!";
        }

        if(ticketService.modifyNumberOfSeats(id,numberSeats) == null){
            return "Could not modify ticket seats";
        }
        return "Ticket seats modified successfully!";
    }

    @PutMapping("logged-in/ticket/modify-name/{id}/{name}")
    public String modifyTicketName(@PathVariable Integer id, @PathVariable String name){
        if(ticketService.modifyTicketName(id,name) == null){
            return "Could not modify ticket name";
        }
        return "Ticket name modified successfully!";
    }

    @PutMapping("logged-in/ticket/modify-show/{id}/{showId}")
    public String modifyTicketShow(@PathVariable Integer id, @PathVariable Integer showId){
        Optional<Show> show = showService.getShowById(showId);
        if(!show.isPresent()){
            return "Could not find show with given id";
        }
        if(ticketService.modifyShow(id, show.get()) == null){
            return "Could not modify ticket show";
        }
        return "Ticket show modified successfully!";
    }

    @PostMapping("/logged-in/ticket/add")
    public String addTicket(@RequestBody Ticket ticket){
        if(ticket.getNumberOfSeats() < 1){
            return "Cannot create ticket with negative number of seats!";
        }
        if(showService.canSellTickets(ticket.getShow().getShowID(), ticket.getNumberOfSeats())){
            if(showService.modifyTicketsSold(ticket.getShow().getShowID(), ticket.getNumberOfSeats()) != null) {
                ticketService.saveTicket(ticket);
                return "New ticket added successfully!";
            }
        }
        return "There are not enough seats available!";
    }

    @GetMapping("/logged-in/ticket/getAllTickets/{id}")
    public List<Ticket> seeAllTickets(@PathVariable Integer id){
        List<Ticket> ticketList = new ArrayList<>();
        for(Ticket ticket: ticketService.getAllTickets()){
            if(ticket.getShow().getShowID() == id){
                ticketList.add(ticket);
            }
        }
        return ticketList;
    }
}
