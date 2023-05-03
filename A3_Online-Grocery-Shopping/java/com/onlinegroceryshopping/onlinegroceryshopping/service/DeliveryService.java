package com.onlinegroceryshopping.onlinegroceryshopping.service;

import com.onlinegroceryshopping.onlinegroceryshopping.model.Customer;
import com.onlinegroceryshopping.onlinegroceryshopping.model.Delivery;
import com.onlinegroceryshopping.onlinegroceryshopping.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public void save(Delivery delivery){
        deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllDeliveries(){
        return deliveryRepository.findAll();
    }

    public Delivery getDelivery(Integer id){
        return deliveryRepository.findById(id).get();
    }

    public void deleteDelivery(Integer id){
        deliveryRepository.deleteById(id);
    }
}
