package com.ticket.ticket_selling_system.service;

import com.ticket.ticket_selling_system.model.Cashier;
import com.ticket.ticket_selling_system.repository.CashierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CashierService {

    @Autowired
    private CashierRepository cashierRepository;

    public Cashier saveCashier(Cashier cashier){
        return cashierRepository.save(cashier);
    }

    public List<Cashier> getAllCashiers(){
        return cashierRepository.findAll();
    }

    public Cashier getCashier(String username, String password){
        for(Cashier cashier : cashierRepository.findAll()){
            String checkPassword= cashier.decryptPassword();
            if(cashier.getUsername().equals(username) && checkPassword.equals(password)){
                return cashier;
            }
        }
        return null;
    }

    public Optional<Cashier> getCashierById(Integer id){
        return cashierRepository.findById(id);
    }
    public void delete(Integer id){
        cashierRepository.deleteById(id);
    }

    public Cashier modifyUsername(Integer id, String username){
        cashierRepository.findById(id).map(cashier -> {
            cashier.setUsername(username);
            return cashierRepository.save(cashier);
        });
        return null;
    }

    public Cashier modifyPassword(Integer id, String password){
        cashierRepository.findById(id).map(cashier -> {
            cashier.setPassword(password);
            return cashierRepository.save(cashier);
        });
        return null;
    }

    public Cashier modifyName(Integer id, String name){
        cashierRepository.findById(id).map(cashier -> {
            cashier.setName(name);
            return cashierRepository.save(cashier);
        });
        return null;
    }
}
