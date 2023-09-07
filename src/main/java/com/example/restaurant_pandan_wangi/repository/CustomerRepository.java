package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}