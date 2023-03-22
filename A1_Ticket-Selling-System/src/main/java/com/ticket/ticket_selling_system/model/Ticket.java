package com.ticket.ticket_selling_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name ="tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketID;

    private int numberOfSeats;
    private String ticketName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "showID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Show show;

    public Ticket() {
    }

    public Ticket(int numberOfSeats, String ticketName, Show show) {
        this.numberOfSeats = numberOfSeats;
        this.ticketName = ticketName;
        this.show = show;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    @Override
    public String toString(){
        return this.ticketID + "," + this.numberOfSeats + "," + this.ticketName + "," + this.show.getShowID();
    }
}
