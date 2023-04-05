package com.laboratorytracker.laboratorytracker.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import java.util.Base64;

@MappedSuperclass
public class User {

    private String email;
    private String password;
    private Boolean isAuthenticated;

    public User() {

    }

    @PrePersist
    public void encryptPassword() {
        if(password != null) {
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            setPassword(encodedPassword);
        }
    }
    public User(String email, String password) {
        //this.email=email;
        //this.password=password;
        this.isAuthenticated = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String decryptPassword() {
        String decodedPassword = new String(Base64.getDecoder().decode(password));
        return decodedPassword;
    }
}
