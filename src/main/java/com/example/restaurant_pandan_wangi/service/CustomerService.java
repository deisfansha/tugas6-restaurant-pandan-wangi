package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.Customer;
import com.example.restaurant_pandan_wangi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    private Customer current;
    private String message;

    public String getMessage() {
        return message;
    }

    public Customer getCurrent() {
        return current;
    }

    /**
     * Menambahkan customer baru.
     *
     * @param customerRequest  customer yang akan ditambahkan.
     * @return              True jika berhasil ditambahkan, dan false jika gagal.
     */
    public boolean add(Customer customerRequest) {
        if (customerRequest.getName() != null && isNameValid(customerRequest.getName())) {
            customerRepository.save(customerRequest);
            message = "Customer added successfully.";
            return true;
        } else {
            message = "Input invalid.";
            return false;
        }
    }

    /**
     * Memperbarui informasi Customer yang ada berdasarkan ID Customer.
     *
     * @param id            ID Customer yang akan diperbarui.
     * @param customerRequest  Informasi Customer yang ingin diperbarui.
     * @return              True jika berhasil diperbarui, dan false jika gagal.
     */
    public boolean updateData(Long id, Customer customerRequest) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        current = null;

        if (!customerOptional.isPresent()) {
            message = "Customer ID Not Found.";
            return false;
        } else if (customerRequest.getName() == null || !isNameValid(customerRequest.getName())) {
            message = "Input invalid.";
            return false;
        } else {
            customerOptional.get().setName(customerRequest.getName());
            customerOptional.get().setPhone(customerRequest.getPhone());
            customerRepository.save(customerOptional.get());
            message = "Customer with ID `" + id + "` updated successfully.";
            current = customerOptional.get();
            return true;
        }
    }

    public boolean updateIsMember(long id, boolean isMember) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (!customerOptional.isPresent()) {
            message = "Customer ID Not Found.";
            return false;
        } else {
            customerOptional.get().setMember(isMember);
            customerRepository.save(customerOptional.get());
            if (isMember) {
                message = "Successfully became a member !";
            }
            current = customerOptional.get();
            return true;
        }
    }

    /**
     * Mengembalikan daftar Customer yang masih tersedia.
     *
     * @return      Daftar Customer yang masih tersedia.
     */
    public List<Customer> customerList() {
        return customerRepository.findAll();
    }

    /**
     * Mengembalikan informasi Customer berdasarkan ID Customer.
     *
     * @param id    ID Customer.
     * @return      Customer jika tersedia, jika tidak tersedia kembalikan null.
     */
    public Customer getCustomerById(long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            message = "Customer ID Found.";
            return customerOptional.get();
        } else {
            message = "Customer ID Not Found.";
            return null;
        }
    }

    /**
     * Memeriksa apakah sebuah nama valid.
     * Nama yang valid hanya mengandung huruf (a-z, A-Z), angka (0-9), dan spasi.
     *
     * @param name      Nama yang akan diperiksa.
     * @return true     Jika nama valid, false jika tidak valid.
     */
    private boolean isNameValid(String name) {
        return name.matches("[a-zA-Z0-9\\s]+");
    }
}