package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.TableNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableNumberRepository extends JpaRepository<TableNumber, Long> {
    List<TableNumber> findAllByIsActiveOrderById(boolean isActive);
    List<TableNumber> findAllByOrderById();
    List<TableNumber> findAllByIsAvailableOrderById(boolean isAvailable);
}
