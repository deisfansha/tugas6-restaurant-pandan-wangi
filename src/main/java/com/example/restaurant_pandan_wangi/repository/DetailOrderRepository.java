package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.DetailOrder;
import com.example.restaurant_pandan_wangi.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailOrderRepository extends JpaRepository<DetailOrder, Long> {
    @Query("SELECT do FROM DetailOrder do ORDER BY do.id")
    List<DetailOrder> findAllDetailOrder();
}
