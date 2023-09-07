package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.TableNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableNumberRepository extends JpaRepository<TableNumber, Long> {
    @Query("SELECT tn FROM TableNumber tn WHERE tn.isActive = :isActive ORDER BY tn.id")
    List<TableNumber> findAllTableByActive(boolean isActive);

    @Query("SELECT tn FROM TableNumber tn ORDER BY tn.id")
    List<TableNumber> findAllTable();

    @Query("SELECT tn FROM TableNumber tn WHERE tn.isTableInUse = :isUse ORDER BY tn.id")
    List<TableNumber> findAllTableByInUse(boolean isUse);
}
