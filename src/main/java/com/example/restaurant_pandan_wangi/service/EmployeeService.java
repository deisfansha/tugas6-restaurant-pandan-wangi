package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restaurant_pandan_wangi.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

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

    public List<Employee> getAllByIsActive(){
        return employeeRepository.findAllNotDeleted();
    }

    public List<Employee> getAll(){
        return employeeRepository.findAllSorting();
    }

    public List<Employee> getAllByPosition(boolean position){
        return employeeRepository.findAllByPosition(position);
    }

    public boolean update(Long id, Employee employeeRequest){
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        List<Employee> existingPhoneNumber = employeeRepository.findFirstByPhoneNumber(employeeRequest.getPhoneNumber());

        if (!existingEmployee.isPresent()){
            message = "Employee not found";
            return false;
        }else if (employeeRequest.getName() == null || employeeRequest.getPhoneNumber() == null){
            message = "Data must be filled in";
            return false;
        } else if (isNameNotValid(employeeRequest.getName())) {
            message = "Name is not valid";
            return false;
        }else if(isPhoneNumberNotValid(employeeRequest.getPhoneNumber())){
            message = "Phone number is not valid";
            return false;
        } else if (existingPhoneNumber.size()>=1) {
            message = "Phone Number already exists";
            return false;
        }


        existingEmployee.get().setName(employeeRequest.getName());
        existingEmployee.get().setPhoneNumber(employeeRequest.getPhoneNumber());
        existingEmployee.get().setPosition(employeeRequest.isPosition());
        employeeRepository.save(existingEmployee.get());
        message = "Success";
        current = existingEmployee.get();
        return true;
    }

    public boolean updateActived(Long id, Employee employeeRequest){
        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        if (!existingEmployee.isPresent()){
            message = "Employee not found";
            return false;
        }

        existingEmployee.get().setActive(employeeRequest.isActive());
        employeeRepository.save(existingEmployee.get());
        message = "Success";
        current = existingEmployee.get();
        return true;
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
