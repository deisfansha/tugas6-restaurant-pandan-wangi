package com.example.restaurant_pandan_wangi.controller;

import com.example.restaurant_pandan_wangi.model.ApiResponse;
import com.example.restaurant_pandan_wangi.model.Order;
import com.example.restaurant_pandan_wangi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public ResponseEntity createOrder(@RequestBody Order orderRequest){
        boolean added = orderService.createOrder(orderRequest);
        if (added){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(orderService.getMessage(), orderRequest));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(orderService.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity getAllPositionEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", orderService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getAllById(@PathVariable Long id){
        if (orderService.getById(id) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(orderService.getMessage()));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", orderService.getById(id)));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable long id){
        boolean deleted = orderService.deleteOrder(id);
        if (deleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(orderService.getMessage(), orderService.getCurrent()));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(orderService.getMessage()));
        }
    }

}
