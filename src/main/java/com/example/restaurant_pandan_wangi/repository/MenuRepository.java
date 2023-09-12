package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByIsActiveOrderById(Boolean isActive);
    List<Menu> findAllByNameContainingOrderById(String name);
    List<Menu> findAllByIsFoodOrderById(Boolean isFood);
    List<Menu> findAllByOrderById();
}
