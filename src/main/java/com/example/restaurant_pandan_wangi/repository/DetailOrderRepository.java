package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.DetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailOrderRepository extends JpaRepository<DetailOrder, Long> {
    List<DetailOrder> findAllByIsDeletedFalseOrderByIdAsc();
    List<DetailOrder> findAllByOrder_IdAndIsDeletedFalseOrderById(Long idOrder);
    Optional<DetailOrder> findByIsDeletedFalseAndId(Long id);
}
