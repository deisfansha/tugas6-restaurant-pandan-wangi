package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.DetailOrder;
import com.example.restaurant_pandan_wangi.model.Menu;
import com.example.restaurant_pandan_wangi.model.Order;
import com.example.restaurant_pandan_wangi.repository.DetailOrderRepository;
import com.example.restaurant_pandan_wangi.repository.MenuRepository;
import com.example.restaurant_pandan_wangi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailOrderService {
    @Autowired
    private DetailOrderRepository detailOrderRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private OrderRepository orderRepository;
    private DetailOrder current;
    private String message;

    public DetailOrder getCurrent() {
        return current;
    }

    public String getMessage() {
        return message;
    }

    public boolean add(DetailOrder detailOrderRequest) {
        if (detailOrderRequest.getOrder() == null) {
            message = "Invalid order";
            return false;
        } else if (detailOrderRequest.getMenu() == null) {
            message = "Invalid menu";
            return false;
        }

        Optional<Order> orderOptional = orderRepository.findById(detailOrderRequest.getOrder().getId());
        Optional<Menu> menuOptional = menuRepository.findById(detailOrderRequest.getMenu().getId());

        if (!menuOptional.isPresent()) {
            message = "Menu Not Found";
            return false;
        } else if (!orderOptional.isPresent()) {
            message = "Order Not Found";
            return false;
        } else if (detailOrderRequest.getPrice() <= 0 && detailOrderRequest.getQuantity() <= 0) {
            message = "Input Invalid";
            return false;
        } else {
            detailOrderRequest.setMenu(menuOptional.get());
            detailOrderRequest.setPrice(menuOptional.get().getPrice());
            detailOrderRepository.save(detailOrderRequest);
            detailOrderRequest.setOrder(orderOptional.get());
            return true;
        }
    }

    public boolean delete(long id) {
        Optional<DetailOrder> optionalDetailOrder = detailOrderRepository.findById(id);
        current = null;

        if (!optionalDetailOrder.isPresent()) {
            message = "DetailOrder Not Found";
            return false;
        } else {
            current = optionalDetailOrder.get();
            detailOrderRepository.delete(optionalDetailOrder.get());
            return true;
        }
    }

    public void deleteByIdOrder(long idOrderRequest) {
        List<DetailOrder> detailOrdersByIdOrder = getAllDetailOrderByIdOrder(idOrderRequest);

        if (detailOrdersByIdOrder.size() == 0) {
            message = "DetailOrder Not Found";
        } else {
            detailOrderRepository.deleteAll(detailOrdersByIdOrder);
        }
    }

    public boolean updateStatusOrder(long id, int status) {
        Optional<DetailOrder> optionalDetailOrder = detailOrderRepository.findById(id);
        current = null;

        if (!optionalDetailOrder.isPresent()) {
            message = "DetailOrder Not Found";
            return false;
        } else if (status < 0 || status > 3) {
            message = "Input Invalid";
            return false;
        }
        else {
            optionalDetailOrder.get().setStatusOrder(status);
            current = optionalDetailOrder.get();
            detailOrderRepository.save(optionalDetailOrder.get());
            return true;
        }
    }

    public List<DetailOrder> getAllDetailOrder() {
        return detailOrderRepository.findAllDetailOrder();
    }

    public List<DetailOrder> getAllDetailOrderByIdOrder(long idOrder) {
        return detailOrderRepository.findAllDetailOrderByIdOrder(idOrder);
    }

    public DetailOrder getDetailOrderById(long id) {
        Optional<DetailOrder> detailOrderOptional = detailOrderRepository.findById(id);

        if (detailOrderOptional.isPresent()) {
            return detailOrderOptional.get();
        } else {
            message = "DetailOrder Not Found.";
            return null;
        }
    }
}
