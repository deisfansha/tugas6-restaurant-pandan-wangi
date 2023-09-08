package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.DetailOrder;
import com.example.restaurant_pandan_wangi.model.Menu;
//import com.example.restaurant_pandan_wangi.model.Order;
import com.example.restaurant_pandan_wangi.repository.DetailOrderRepository;
import com.example.restaurant_pandan_wangi.repository.MenuRepository;
//import com.example.restaurant_pandan_wangi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetailOrderService {
    @Autowired
    private DetailOrderRepository detailOrderRepository;
    @Autowired
    private MenuRepository menuRepository;
//    @Autowired
//    private OrderRepository orderRepository;
    private DetailOrder current;
    private String message;

    public DetailOrder getCurrent() {
        return current;
    }

    public String getMessage() {
        return message;
    }

    public List<DetailOrder> getAllDetailOrder() {
        return detailOrderRepository.findAllDetailOrder();
    }

    public List<DetailOrder> gettAllDetailOrderByIdOrder(long idOrder) {
        return new ArrayList<>();
    }

    public boolean add(DetailOrder detailOrderRequest) {
//        if (detailOrderRequest.getOrder() == null) {
//            message = "Please insert ID Order.";
//            return false;
//        } else
        if (detailOrderRequest.getMenu() == null) {
            message = "Please insert ID Menu.";
            return false;
        }

//        Optional<Order> orderOptional = orderRepository.findById(detailOrderRequest.getOrder().getId());
        Optional<Menu> menuOptional = menuRepository.findById(detailOrderRequest.getMenu().getId());

        if (!menuOptional.isPresent()) {
            message = "Menu ID not found.";
            return false;
        }
//        else if (!orderOptional.isPresent()) {
//            message = "Order ID not found.";
//            return false;
//        }
        else if (detailOrderRequest.getPrice() <= 0 && detailOrderRequest.getQuantity() <= 0) {
            message = "Input invalid.";
            return false;
        } else {
            System.out.println("Harga : " + menuOptional.get().getPrice());
            detailOrderRequest.setMenu(menuOptional.get());
            detailOrderRequest.setPrice(menuOptional.get().getPrice());
            detailOrderRepository.save(detailOrderRequest);
//            detailOrderRequest.setOrder(orderOptional.get());
            message = "Menu added successfully.";
            return true;
        }
    }

    public boolean delete(long id) {
        Optional<DetailOrder> optionalDetailOrder = detailOrderRepository.findById(id);
        current = null;

        if (!optionalDetailOrder.isPresent()) {
            message = "DetailOrder ID not found.";
            return false;
        } else {
            message = "DetailOrder with ID `" + id + "` deleted successfully.";
            current = optionalDetailOrder.get();
            detailOrderRepository.delete(optionalDetailOrder.get());
            return true;
        }
    }


}
