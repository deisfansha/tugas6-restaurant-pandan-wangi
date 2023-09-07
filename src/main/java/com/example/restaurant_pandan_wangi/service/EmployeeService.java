package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant_pandan_wangi.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee current;
    private String message;

    public String getMessage(){
        return message;
    }

    public Employee getCurrent(){
        return current;
    }

    // Method tambah pegawai
    public boolean create(Employee employeeRequest){
        if (employeeRequest.getName() == null || employeeRequest.getPhoneNumber() == null){
            message = "Data must be filled in";
            return false;
        } else if (isNameNotValid(employeeRequest.getName())) {
            message = "Name is not valid";
            return false;
        }else if(isPhoneNumberNotValid(employeeRequest.getPhoneNumber())){
            message = "Phone number is not valid";
            return false;
        }
        employeeRepository.save(employeeRequest);
        message = "Employee added successfully";
        return true;
    }

    public List<Employee> getAll(){
        return employeeRepository.findAllNotDeleted();
    }

    // Validasi nama
    private boolean isNameNotValid(String name) {
        return !name.matches("[a-zA-Z0-9\\s]+"); // Nama harus sesuai dengan format
    }

    // Validasi Phone Number
    private boolean isPhoneNumberNotValid(String phoneNumber){
        // Phone number harus berupa angka dan tidak boleh lebih dari 13 angka
        return !phoneNumber.matches("^[0-9]{8,13}$");
    }
}
