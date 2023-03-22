package com.ticket.ticket_selling_system.model;

import jakarta.persistence.*;

import java.util.Base64;

@Entity
@Table(name ="admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminID;

    private String username;
    private String password;

    @PrePersist
    public void encryptPassword() {
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        setPassword(encodedPassword);
    }

    public Admin(){

    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
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


    public String decryptPassword() {
        String decodedPassword = new String(Base64.getDecoder().decode(password));
        return decodedPassword;
    }
}
