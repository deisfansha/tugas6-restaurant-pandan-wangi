package com.example.restaurant_pandan_wangi.controller;

import com.example.restaurant_pandan_wangi.model.ApiResponse;
import com.example.restaurant_pandan_wangi.model.DetailOrder;
import com.example.restaurant_pandan_wangi.service.DetailOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    // API untuk menampilkan semua daftar DetailOrder.
    @GetMapping("")
    public ResponseEntity getAllDetailOrder() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "Success",
                        detailOrderService.getAllDetailOrder()
                ));
    }

    // API untuk menampilkan daftar DetailOrder berdasarkan ID Order
    @GetMapping("/order/{idOrder}")
    public ResponseEntity getAllDetailOrderByIdOrder(@PathVariable long idOrder) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "Success",
                        detailOrderService.getAllDetailOrderByIdOrder(idOrder)
                ));
    }

    // API untuk menampilkan informasi DetailOrder berdasarkan ID DetailOrder.
    @GetMapping("{id}")
    public ResponseEntity getAllDetailOrderById(@PathVariable long id) {
        if (detailOrderService.getDetailOrderById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse(
                            "Success",
                            detailOrderService.getDetailOrderById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(
                            detailOrderService.getMessage()
                    ));
        }

    }

    // API untuk menambahkan DetailOrder berdasarkan request.
    @PostMapping("")
    public ResponseEntity addDetailOrder(@RequestBody DetailOrder detailOrderRequest) {
        if (detailOrderService.add(detailOrderRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(
                            "Success",
                            detailOrderRequest
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            detailOrderService.getMessage()
                    ));
        }
    }
    // API untuk menghapus transaksi DetailOrder berdasarkan ID DetailOrder.
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDetailOrder(@PathVariable long id) {
        if (detailOrderService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            "Success",
                            detailOrderService.getDetailOrderById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            detailOrderService.getMessage()
                    ));
        }
    }

    // API untuk memperbarui status orderan berdasarkan ID DetailOrder.
    @PatchMapping("/status/{id}")
    public ResponseEntity updateStatusOrder(@PathVariable long id, @RequestBody DetailOrder detailOrderRequest) {
        if (detailOrderService.updateStatusOrder(id, detailOrderRequest.getStatusOrder())) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            "Success",
                            detailOrderService.getDetailOrderById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            detailOrderService.getMessage()
                    ));
        }
    }
}
