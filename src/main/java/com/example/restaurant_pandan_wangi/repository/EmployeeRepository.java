package com.example.restaurant_pandan_wangi.repository;

import com.example.restaurant_pandan_wangi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    @Query("SELECT e from Employee e where e.isActive = true ORDER BY e.id")
    List<Employee> findAllNotDeleted();

    @Query("SELECT e from Employee e ORDER BY e.id ASC")
    List<Employee> findAllSorting();

    @Query("SELECT e from Employee e where e.isActive = true and e.position = :position ORDER BY e.id ASC")
    List<Employee> findAllByPosition(boolean position);

    @Query("SELECT e from Employee e where e.phoneNumber = :phoneNumber ORDER BY e.id")
    List<Employee> findFirstByPhoneNumber(String phoneNumber);

    Optional<Employee> findById(Long id);


}
