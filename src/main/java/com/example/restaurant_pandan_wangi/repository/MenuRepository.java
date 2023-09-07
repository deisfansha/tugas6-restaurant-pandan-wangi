package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT m FROM Menu m WHERE m.isActive = :isActive ORDER BY m.id")
    List<Menu> findMenuByActive(boolean isActive);
    @Query("SELECT m FROM Menu m WHERE m.name LIKE %:name% ORDER BY m.id")
    List<Menu> findMenuByName(String name);
    @Query("SELECT m FROM Menu m WHERE m.category = :category ORDER BY m.id")
    List<Menu> findMenuByCategory(boolean category);
    @Query("SELECT m FROM Menu m ORDER BY m.id")
    List<Menu> findAllMenu();
}
