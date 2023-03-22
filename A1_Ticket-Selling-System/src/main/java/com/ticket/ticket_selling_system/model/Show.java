package com.ticket.ticket_selling_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showID;

    private String title;
    private String artist;
    private String genre;

    @Column(name = "show_date")
    private String date;

    @Column(name = "show_time")
    private String time;

    private int maximumNumberTickets;
    private int ticketsSold;

    public Show() {
    }

    public Show(String title, String artist, String genre, String date, String time, int maximumNumberTickets, int ticketsSold) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.date = date;
        this.time = time;
        this.maximumNumberTickets = maximumNumberTickets;
        this.ticketsSold = ticketsSold;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMaximumNumberTickets() {
        return maximumNumberTickets;
    }

    public void setMaximumNumberTickets(int maximumNumberTickets) {
        this.maximumNumberTickets = maximumNumberTickets;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

}
