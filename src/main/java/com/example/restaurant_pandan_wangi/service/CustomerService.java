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
     * Menambahkan Customer baru.
     *
     * @param customerRequest   Customer yang akan ditambahkan.
     * @return                  True jika berhasil ditambahkan, false jika gagal.
     */
    public boolean add(Customer customerRequest) {
        if (isNameValid(customerRequest.getName()) || isPhoneValid(customerRequest.getPhone())){
            message = "Invalid Input.";
            return false;
        }

        customerRepository.save(customerRequest);
        return true;
    }

    /**
     * Memperbarui informasi Customer yang ada berdasarkan ID Customer.
     *
     * @param id                ID Customer yang akan diperbarui.
     * @param customerRequest   Informasi Customer yang ingin diperbarui.
     * @return                  True jika berhasil diperbarui, false jika gagal.
     */
    public boolean updateData(Long id, Customer customerRequest) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        current = null;

        if (!customerOptional.isPresent()) {
            message = "Customer ID Not Found.";
            return false;
        } else if (customerRequest.getName() == null || !isNameValid(customerRequest.getName()) || !isPhoneValid(customerRequest.getPhone()) ) {
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

<<<<<<< HEAD

=======
    /**
     * Memperbarui status member Customer yang ada berdasarkan ID Customer.
     *
     * @param id        ID Customer yang akan diperbarui.
     * @param isMember  Status member Customer yang ingin diperbarui.
     * @return          True jika berhasil diperbarui, dan false jika gagal.
     */
>>>>>>> alfaro
    public boolean updateIsMember(long id, boolean isMember) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (!customerOptional.isPresent()) {
            message = "Customer ID Not Found.";
            return false;
        } else {
            customerOptional.get().setMember(isMember);
            customerRepository.save(customerOptional.get());
            if (isMember) {
                message = "Success";
            }
            current = customerOptional.get();
            return true;
        }
    }

    /**
     * Mengembalikan daftar Customer.
     *
     * @return      Daftar Customer.
     */
    public List<Customer> customerList() {
        if (customerRepository.count() == 0) seed();
        return customerRepository.findAll();
    }

    /**
     * Mengembalikan informasi Customer berdasarkan ID Customer.
     *
     * @param id    ID Customer.
     * @return      Customer jika tersedia, null jika tidak tersedia.
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
     * @return          True jika nama valid, false jika tidak valid.
     */
    private boolean isNameValid(String name) {
        return name == null || !name.matches("[a-zA-Z0-9\\s]+");
    }

    /**
     * Memeriksa apakah sebuah nomor telepon valid.
     * Nomor telpon yang valid hanya mengandung angka dan memiliki 8-13 digit.
     *
     * @param phone_number  Nomor telepon yang akan diperiksa.
     * @return              True jika nomor telepon valid, false jika tidak valid.
     */
    private boolean isPhoneValid(String phone_number){
        return phone_number == null || !phone_number.matches("^[0-9]{8,13}$");
    }

    private void seed() {
        customerRepository.save(new Customer("Alice", "085748293829", false));
        customerRepository.save(new Customer("Bob", "0812938437", false));
        customerRepository.save(new Customer("Carol", "08937389238", false));
        customerRepository.save(new Customer("Frank", "082174839823", true));
        customerRepository.save(new Customer("Grace", "0853849283", true));
    }
}