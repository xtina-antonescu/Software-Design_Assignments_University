package com.ticket.ticket_selling_system.service;

import com.ticket.ticket_selling_system.model.Ticket;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class Factory {
    public void writeToCSV(List<Ticket> ticketList, String header) throws IOException {
        PrintWriter writer = new PrintWriter("D:\\1.FACULTA\\AN3\\SEM2\\SD\\LABS\\Assignment1\\Ticket_Selling_System_Untold\\ticket_selling_system\\src\\main\\resources/tickets.csv");
        writer.println(header);
        for (Ticket ticket : ticketList) {
            writer.println(ticket.toString());
        }
        writer.close();
        System.out.println("tickets exported");
    }
}
