package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.Employee;
import com.example.restaurant_pandan_wangi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (isNameNotValid(employeeRequest.getName()) || isPhoneNumberNotValid(employeeRequest.getPhoneNumber())){
            message = "Input Invalid";
            return false;
        }

        employeeRepository.save(employeeRequest);
        message = "Success";
        return true;
    }

    public List<Employee> getAllByIsActive(){
        return employeeRepository.findAllNotDeleted();
    }

    public List<Employee> getById(Long id){
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (!existingEmployee.isPresent()){
            message = "Employee not found";
            return null;
        }
        return employeeRepository.findAllById(id);
    }

    public List<Employee> getAll(){
        return employeeRepository.findAllSorting();
    }

    public List<Employee> getAllByPosition(boolean position){
        return employeeRepository.findAllByPosition(position);
    }

    public boolean update(long id, Employee employeeRequest){

        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (!existingEmployee.isPresent()){
            message= "Employee Not Found";
            return false;
        }

        if (isNameNotValid(employeeRequest.getName())
        || isPhoneNumberNotValid(employeeRequest.getPhoneNumber())){
            message = "Input Invalid ";
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

    public boolean updateActived(long id, Employee employeeRequest){
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
        return name == null || !name.matches("[a-zA-Z0-9\\s]+"); // Nama harus sesuai dengan format
    }

    // Validasi Phone Number
    private boolean isPhoneNumberNotValid(String phoneNumber){
        // Phone number harus berupa angka dan tidak boleh lebih dari 13 angka
        return phoneNumber == null || !phoneNumber.matches("^[0-9]{8,13}$");
    }
}
