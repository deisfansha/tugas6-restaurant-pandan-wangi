package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.Customer;
import com.example.restaurant_pandan_wangi.model.Employee;
import com.example.restaurant_pandan_wangi.model.Order;
import com.example.restaurant_pandan_wangi.model.TableNumber;
import com.example.restaurant_pandan_wangi.repository.CustomerRepository;
import com.example.restaurant_pandan_wangi.repository.EmployeeRepository;
import com.example.restaurant_pandan_wangi.repository.OrderRepository;
import com.example.restaurant_pandan_wangi.repository.TableNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TableNumberRepository tableNumberRepository;
    @Autowired
    private DetailOrderService detailOrderService;
    @Autowired
    private TableNumberService tableNumberService;
    private String message;

    public String getMessage(){
        return message;
    }

    /**
     * Menambahkan Order baru.
     *
     * @param orderRequest  Order yang akan ditambahkan.
     * @return              True jika berhasil ditambahkan, false jika gagal.
     */
    public boolean createOrder(Order orderRequest){
        if (orderRequest.getCustomer() == null || orderRequest.getEmployee() == null){
            message = "Invalid Input.";
            return false;
        }

        Optional<Employee> existingEmployee = employeeRepository.findById(orderRequest.getEmployee().getId());
        Optional<Customer> existingCustomer = customerRepository.findById(orderRequest.getCustomer().getId());

        if (!existingEmployee.isPresent()){
            message = "Employee Not Found.";
            return false;
        } else if (!existingCustomer.isPresent()) {
            message = "Customer Not Found.";
            return false;
        } else if (!existingEmployee.get().isPosition()){
            message = "Invalid Position To Create Order";
            return false;
        } else if (orderRequest.getTableNumber() != null){
            Optional<TableNumber> tableNotUse = tableNumberRepository.findById(orderRequest.getTableNumber().getId());
            if (tableNotUse.isPresent() && !tableNotUse.get().isAvailable()){
                tableNotUse.get().setAvailable(true);
                orderRequest.setTableNumber(tableNotUse.get());
                tableNumberRepository.save(tableNotUse.get());
            }else {
                message = "Table Not Found.";
                return false;
            }
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String created = format.format(new Timestamp(System.currentTimeMillis()));

        orderRequest.setCustomer(existingCustomer.get());
        orderRequest.setEmployee(existingEmployee.get());
        orderRequest.setCreatedAt(Date.valueOf(created));
        orderRepository.save(orderRequest);
        return true;
    }

    /**
     * Mengembalikan semua daftar Order.
     *
     * @return          Daftar Order.
     */
    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    /**
     * Mengembalikan Order berdasarkan ID Order.
     *
     * @param id    ID Order.
     * @return      Order jika tersedia, null jika tidak tersedia.
     */
    public List<Order> getById(Long id){
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (!existingOrder.isPresent()){
            message = "Order Not Found.";
            return null;
        }
        return orderRepository.findAllById(id);
    }

    /**
     * Menghapus Order beserta DetailOrder berdasarkan ID Order.
     *
     * @param orderId   ID Order yang akan dihapus.
     * @return          True jika berhasil dihapus, false jika gagal.
     */
    public boolean softDeleteOrder(Long orderId){
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (!existingOrder.isPresent()){
            message = "Order Not Found.";
            return false;
        } else if (existingOrder.get().getTableNumber() != null){
            tableNumberService.updateInUse(existingOrder.get().getTableNumber().getId(), false);
        }

        existingOrder.get().setDelete();
        detailOrderService.deleteByIdOrder(orderId);
        orderRepository.save(existingOrder.get());
        return true;
    }
}
