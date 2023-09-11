package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllById(long id);
}
