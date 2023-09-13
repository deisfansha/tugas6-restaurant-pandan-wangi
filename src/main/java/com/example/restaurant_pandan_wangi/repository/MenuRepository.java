package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByIsActiveOrderByNameAsc(Boolean isActive);
    List<Menu> findAllByNameContainingOrderByNameAsc(String name);
    List<Menu> findAllByIsFoodOrderByNameAsc(Boolean isFood);
    List<Menu> findAllByOrderByNameAsc();
}
