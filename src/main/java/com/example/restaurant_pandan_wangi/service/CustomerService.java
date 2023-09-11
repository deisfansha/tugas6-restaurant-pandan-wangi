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
        if (customerRequest.getName() != null && isNameValid(customerRequest.getName()) && isPhoneValid(customerRequest.getPhone())) {
            customerRepository.save(customerRequest);
            message = "Customer added successfully.";
            return true;
        } else {
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
        if (customerRepository.count() == 0) seed();
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

    private void seed() {
        customerRepository.save(new Customer("Alice", "085748293829", false));
        customerRepository.save(new Customer("Bob", "0812938437", false));
        customerRepository.save(new Customer("Carol", "08937389238", false));
        customerRepository.save(new Customer("Frank", "082174839823", true));
        customerRepository.save(new Customer("Grace", "0853849283", true));
    }

    private boolean isPhoneValid(String phone_number) {
        if (phone_number == null) {
            message = "The value of `phone` cannot be null!";
            return false;
        } else if (phone_number.trim().isEmpty()) {
            message = "The value of `phone` cannot be empty!";
            return false;
        } else if (!phone_number.matches("\\d+")) {
            message = "The value of `phone` must be a number.";
            return false;
        } else if (phone_number.length() < 10 || phone_number.length() > 13) {
            message = "The value of `phone` must be a number between 10 and 13.";
            return false;
        } else return true;
    }
}