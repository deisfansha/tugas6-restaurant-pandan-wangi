package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {
}
