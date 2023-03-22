package com.ticket.ticket_selling_system.service;

import com.ticket.ticket_selling_system.model.Admin;
import com.ticket.ticket_selling_system.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    public Admin getAdmin(String username, String password){
        for(Admin admin: adminRepository.findAll()){
            String checkPassword = admin.decryptPassword();
            if(admin.getUsername().equals(username) && checkPassword.equals(password)){
                return admin;
            }
        }
        return null;
    }

    public void delete(Integer id){
        adminRepository.deleteById(id);
    }
}
