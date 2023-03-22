package com.ticket.ticket_selling_system.repository;

import com.ticket.ticket_selling_system.model.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashierRepository extends JpaRepository<Cashier,Integer> {
}
