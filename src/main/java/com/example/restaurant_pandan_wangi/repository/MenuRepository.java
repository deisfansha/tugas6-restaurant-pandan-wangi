package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
