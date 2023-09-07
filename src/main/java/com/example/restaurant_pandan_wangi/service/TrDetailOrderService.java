package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.TrDetailOrder;
import com.example.restaurant_pandan_wangi.repository.TrDetailOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrDetailOrderService {
    @Autowired
    TrDetailOrderRepository trDetailOrderRepository;
    private TrDetailOrder current;
    private String message;

    public TrDetailOrder getCurrent() {
        return current;
    }

    public String getMessage() {
        return message;
    }


}
