package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {
    List<Employee> findAllByIsActiveTrueOrderByNameAsc();
    List<Employee> findByOrderByNameAsc();

    List<Employee> findByPosition(Boolean position);

}
