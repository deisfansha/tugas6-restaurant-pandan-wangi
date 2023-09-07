package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.Employee;
import com.example.restaurant_pandan_wangi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private Employee current;
    private String message;

    public String getMessage(){
        return message;
    }

    public Employee getCurrent(){
        return current;
    }
}
