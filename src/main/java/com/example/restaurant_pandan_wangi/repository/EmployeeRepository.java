package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    @Query("SELECT e from Employee e where e.isActive = true ORDER BY e.id")
    List<Employee> findAllNotDeleted();
}
