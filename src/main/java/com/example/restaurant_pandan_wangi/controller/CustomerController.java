package com.example.restaurant_pandan_wangi.controller;

import com.example.restaurant_pandan_wangi.model.ApiResponse;
import com.example.restaurant_pandan_wangi.model.Customer;
import com.example.restaurant_pandan_wangi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity getCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Customer.",
                        customerService.customerList()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable long id) {
        if (customerService.getCustomerById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            customerService.getMessage(),
                            customerService.getCustomerById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            customerService.getMessage()
                    ));
        }
    }

    @PostMapping("")
    public ResponseEntity addCustomer(@RequestBody Customer customerRequest) {
        if (customerService.add(customerRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(
                            customerService.getMessage(),
                            customerRequest
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            customerService.getMessage()
                    ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable long id, @RequestBody Customer customerRequest) {
        if (customerService.updateData(id, customerRequest)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            customerService.getMessage(),
                            customerService.getCurrent()
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            customerService.getMessage()
                    ));
        }
    }

    @PatchMapping("/member/{id}")
    public ResponseEntity updateIsMember(@PathVariable long id, @RequestBody Customer customerRequest) {
        if (customerService.updateIsMember(id,customerRequest.isMember())) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            customerService.getMessage(),
                            customerService.getCurrent()
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            customerService.getMessage()
                    ));
        }
    }
}