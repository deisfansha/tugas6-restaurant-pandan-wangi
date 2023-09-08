package com.example.restaurant_pandan_wangi.controller;

import com.example.restaurant_pandan_wangi.model.ApiResponse;
import com.example.restaurant_pandan_wangi.model.DetailOrder;
import com.example.restaurant_pandan_wangi.model.Menu;
import com.example.restaurant_pandan_wangi.service.DetailOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detail-orders")
public class DetailOrderController {
    @Autowired
    private DetailOrderService detailOrderService;

    @GetMapping("")
    public ResponseEntity getAllDetailOrder() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Detail Order.",
                        detailOrderService.getAllDetailOrder()
                ));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity getAllDetailOrderByIdOrder(@PathVariable long idOrder) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Detail Order.",
                        detailOrderService.gettAllDetailOrderByIdOrder(idOrder)
                ));
    }

    @PostMapping("")
    public ResponseEntity addDetailOrder(@RequestBody DetailOrder detailOrderRequest) {
        if (detailOrderService.add(detailOrderRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(
                            detailOrderService.getMessage(),
                            detailOrderRequest
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            detailOrderService.getMessage()
                    ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDetailOrder(@PathVariable long id) {
        if (detailOrderService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            detailOrderService.getMessage(),
                            detailOrderService.getCurrent()
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            detailOrderService.getMessage()
                    ));
        }
    }
}
