package com.ticket.ticket_selling_system.controller;

import com.ticket.ticket_selling_system.model.Admin;
import com.ticket.ticket_selling_system.model.Cashier;
import com.ticket.ticket_selling_system.model.Show;
import com.ticket.ticket_selling_system.model.Ticket;
import com.ticket.ticket_selling_system.service.AdminService;
import com.ticket.ticket_selling_system.service.CashierService;
import com.ticket.ticket_selling_system.service.ShowService;
import com.ticket.ticket_selling_system.service.TicketService;
import com.ticket.ticket_selling_system.service.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CashierService cashierService;

    @Autowired
    private ShowService showService;

    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    public String add(@RequestBody Admin admin){
        adminService.saveAdmin(admin);
        return "New admin is added!";
    }

    @GetMapping("/getAll")
    public List<Admin> list(){
        return adminService.getAllAdmins();
    }

    @GetMapping("/log-in/{username}/{password}")
    public String logIn(@PathVariable String username, @PathVariable String password){
        if(adminService.getAdmin(username,password) != null){
            return "Admin logged-in successfully!";
        }

        return "Could not log-in!";
    }

    @GetMapping("/logged-in/cashier/get/{id}")
    public Optional<Cashier> getCashier(@PathVariable Integer id){
        return cashierService.getCashierById(id);
    }

    @PostMapping("/logged-in/cashier/add")
    public String addCashier(@RequestBody Cashier cashier){
        cashierService.saveCashier(cashier);
        return "New cashier is added";
    }

    @DeleteMapping("/logged-in/cashier/delete/{id}")
    public String deleteCashier(@PathVariable Integer id){
        cashierService.delete(id);
        return "Cashier deleted successfully!";
    }

    @PutMapping("/logged-in/cashier/modify/username/{id}/{username}")
    public String modifyCashierUsername(@PathVariable Integer id, @PathVariable String username){
        if(cashierService.modifyUsername(id,username) == null){
            return "Could not modify cashier username";
        }

        return "Modified cashier username successfully!";
    }

    @PutMapping("/logged-in/cashier/modify/password/{id}/{password}")
    public String modifyCashierPassword(@PathVariable Integer id, @PathVariable String password){
        if(cashierService.modifyPassword(id,password) == null){
            return "Could not modify cashier password";
        }

        return "Modified cashier password successfully!";
    }

    @PutMapping("/logged-in/cashier/modify/name/{id}/{name}")
    public String modifyCashierName(@PathVariable Integer id, @PathVariable String name){
        if(cashierService.modifyName(id,name) == null){
            return "Could not modify cashier name";
        }
        return "Modified cashier name successfully!";
    }

    @PostMapping("logged-in/show/add")
    public String addShow(@RequestBody Show show){
        showService.saveShow(show);
        return "New show is added!";
    }

    @PutMapping("/logged-in/show/modify/title/{id}/{title}")
    public String modifyShowTitle(@PathVariable Integer id, @PathVariable String title){
        if(showService.modifyTime(id,title) == null){
            return "Could not modify show name!";
        }
        return "Modified show title successfully!";
    }

    @PutMapping("/logged-in/show/modify/artist/{id}/{artist}")
    public String modifyShowArtist(@PathVariable Integer id, @PathVariable String artist){
        if(showService.modifyArtist(id, artist) == null){
            return "Could not modify show artist!";
        }
        return "Modified show artist successfully!";
    }

    @PutMapping("/logged-in/show/modify/genre/{id}/{genre}")
    public String modifyShowGenre(@PathVariable Integer id, @PathVariable String genre){
        if(showService.modifyGenre(id, genre) == null){
            return "Could not modify show genre!";
        }
        return "Modified show genre successfully!";
    }

    @PutMapping("/logged-in/show/modify/date/{id}/{date}")
    public String modifyShowDate(@PathVariable Integer id, @PathVariable String date){
        if(showService.modifyDate(id, date) == null){
            return "Could not modify show date!";
        }
        return "Modified show date successfully!";
    }

    @PutMapping("/logged-in/show/modify/time/{id}/{time}")
    public String modifyShowTime(@PathVariable Integer id, @PathVariable String time){
        if(showService.modifyTime(id, time) == null){
            return "Could not modify show time!";
        }
        return "Modified show time successfully!";
    }

    @PutMapping("/logged-in/show/modify/maximum-number-tickets/{id}/{maxNumTickets}")
    public String modifyShowMaxNumTickets(@PathVariable Integer id, @PathVariable int maxNumTickets){
        if(showService.modifyMaximumNumber(id, maxNumTickets) == null){
            return "Could not modify show maximum number of tickets!";
        }
        return "Modified show maximum number of tickets successfully!";
    }

    @GetMapping("/logged-in/show/export/{id}")
    public List<Ticket> exportTickets(@PathVariable Integer id) throws IOException {
        List<Ticket> ticketList = new ArrayList<>();
        for(Ticket ticket: ticketService.getAllTickets()){
            if(ticket.getShow().getShowID() == id){
                ticketList.add(ticket);
            }
        }
        String tableHeader = "Ticket_ID, Number_Of_Seats, Ticket_Name, Show_Title";
        Factory factory = new Factory();
        factory.writeToCSV(ticketList, tableHeader);
        return ticketList;
    }

}
