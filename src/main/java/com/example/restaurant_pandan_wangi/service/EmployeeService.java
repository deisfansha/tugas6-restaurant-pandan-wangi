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

    /**
     * Menambahkan Employee baru.
     *
     * @param employeeRequest   Employee yang akan ditambahkan.
     * @return                  True jika berhasil ditambahkan, dan false jika gagal.
     */
    public boolean create(Employee employeeRequest){
        if (isNameNotValid(employeeRequest.getName()) || isPhoneNumberNotValid(employeeRequest.getPhoneNumber())){
            message = "Invalid Input.";
            return false;
        }

        employeeRepository.save(employeeRequest);
        return true;
    }

    /**
     * Mengembalikan daftar Employee berdasarkan status aktif.
     *
     * @return          Daftar Employee.
     */
    public List<Employee> getAllByIsActive(){
        return employeeRepository.findAllByIsActiveTrueOrderByNameAsc();
    }

    /**
     * Mengembalikan Employee berdasarkan ID Employee.
     *
     * @param id    ID Employee.
     * @return      Employee jika tersedia, jika tidak tersedia kembalikan null.
     */
    public List<Employee> getById(Long id){
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (!existingEmployee.isPresent()){
            message = "Employee Not Found.";
            return null;
        }
        return employeeRepository.findAllById(id);
    }

    /**
     * Mengembalikan semua daftar Employee.
     *
     * @return          Daftar Employee.
     */
    public List<Employee> getAll(){
        if (employeeRepository.count() == 0) seed();
        return employeeRepository.findAllByOrderByNameAsc();
    }

    /**
     * Mengembalikan daftar Employee berdasarkan posisi.
     *
     * @return          Daftar Employee.
     */
    public List<Employee> getAllByPosition(boolean position){
        return employeeRepository.findByPosition(position);
    }

    /**
     * Memperbarui informasi Employee yang ada berdasarkan ID Employee.
     *
     * @param id                ID Employee yang akan diperbarui.
     * @param employeeRequest   Informasi Employee yang ingin diperbarui.
     * @return                  True jika berhasil diperbarui, false jika gagal.
     */
    public boolean update(long id, Employee employeeRequest){
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (!existingEmployee.isPresent()){
            message= "Employee Not Found.";
            return false;
        } else if (isNameNotValid(employeeRequest.getName()) || isPhoneNumberNotValid(employeeRequest.getPhoneNumber())){
            message = "Invalid Input.";
            return false;
        }

        existingEmployee.get().setName(employeeRequest.getName());
        existingEmployee.get().setPhoneNumber(employeeRequest.getPhoneNumber());
        existingEmployee.get().setPosition(employeeRequest.isPosition());
        employeeRepository.save(existingEmployee.get());
        current = existingEmployee.get();
        return true;
    }

    /**
     * Memperbarui status bekerja Employee yang ada berdasarkan ID Employee.
     *
     * @param id                ID Employee yang akan diperbarui.
     * @param employeeRequest   Status bekerja Employee yang ingin diperbarui.
     * @return                  True jika berhasil diperbarui, false jika gagal.
     */
    public boolean updateActivated(long id, Employee employeeRequest){
        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        if (!existingEmployee.isPresent()){
            message = "Employee Not Found.";
            return false;
        }

        existingEmployee.get().setActive(employeeRequest.isActive());
        employeeRepository.save(existingEmployee.get());
        current = existingEmployee.get();
        return true;
    }

    /**
     * Memeriksa apakah sebuah nama valid.
     * Nama yang valid hanya mengandung huruf (a-z, A-Z), angka (0-9), dan spasi.
     *
     * @param name      Nama yang akan diperiksa.
     * @return true     Jika nama valid, false jika tidak valid.
     */
    private boolean isNameNotValid(String name) {
        return name == null || !name.matches("[a-zA-Z0-9\\s]+"); // Nama harus sesuai dengan format
    }

    /**
     * Memeriksa apakah sebuah nomor telepon valid.
     * Nomor telpon yang valid hanya mengandung angka dan memiliki 8-13 digit.
     *
     * @param phoneNumber   Nomor telepon yang akan diperiksa.
     * @return true         True jika nomor telepon valid, false jika tidak valid.
     */
    private boolean isPhoneNumberNotValid(String phoneNumber){
        return phoneNumber == null || !phoneNumber.matches("^[0-9]{8,13}$");
    }

    private void seed() {
        employeeRepository.save(new Employee("Jack", "08213748382", false));
        employeeRepository.save(new Employee("Grace", "08938493829", false));
        employeeRepository.save(new Employee("Noah", "08183947584", true));
        employeeRepository.save(new Employee("Wiliam", "0868439384", true));
        employeeRepository.save(new Employee("Victoria", "08183947382", true));
    }
}
