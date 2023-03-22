package com.ticket.ticket_selling_system.model;

import jakarta.persistence.*;

import java.util.Base64;

@Entity
@Table(name = "cashiers")
public class Cashier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cashierID;

    private String username;
    private String password;
    private String name;

    public Cashier(){

    }

    @PrePersist
    public void encryptPassword() {
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        setPassword(encodedPassword);
    }

    public Cashier(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public int getCashierID() {
        return cashierID;
    }

    public void setCashierID(int cashierID) {
        this.cashierID = cashierID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String decryptPassword() {
        String decodedPassword = new String(Base64.getDecoder().decode(password));
        return decodedPassword;
    }
}
