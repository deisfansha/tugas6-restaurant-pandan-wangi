package com.example.restaurant_pandan_wangi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrDetailOrderRepository extends JpaRepository<TrDetailOrderRepository, Long> {

}
