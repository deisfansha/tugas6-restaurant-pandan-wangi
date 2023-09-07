package com.example.restaurant_pandan_wangi.controller;

import com.example.restaurant_pandan_wangi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


}
