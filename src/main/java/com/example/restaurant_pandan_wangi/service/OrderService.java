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

    private Order current;
    private String message;

    public String getMessage(){
        return message;
    }

    public Order getCurrent(){
        return current;
    }

    public boolean createOrder(Order orderRequest){
        if (orderRequest.getCustomer() == null || orderRequest.getEmployee() == null){
            message = "Invalid Input";
            return false;
        }

        Optional<Employee> existingEmployee = employeeRepository.findById(orderRequest.getEmployee().getId());
        Optional<Customer> existingCustomer = customerRepository.findById(orderRequest.getCustomer().getId());


        if (!existingEmployee.isPresent() || !existingCustomer.isPresent()){
            message = "Invalid Data";
            return false;
        }

        if (orderRequest.getTableNumber() != null){
            Optional<TableNumber> tableNotUse = tableNumberRepository.findById(orderRequest.getTableNumber().getId());
            if (tableNotUse.isPresent() && !tableNotUse.get().isTableInUse()){
                tableNotUse.get().setTableInUse(true);
                orderRequest.setTableNumber(tableNotUse.get());
                tableNumberRepository.save(tableNotUse.get());
            }else {
                message = "Table not found";
                return false;
            }
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String created = format.format(new Timestamp(System.currentTimeMillis()));

        orderRequest.setCustomer(existingCustomer.get());
        orderRequest.setEmployee(existingEmployee.get());
        orderRequest.setCreatedAt(Date.valueOf(created));
        orderRepository.save(orderRequest);
        message = "Success";
        return true;
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public List<Order> getById(Long id){
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (!existingOrder.isPresent()){
            message = "Order Not Found";
            return null;
        }
        return orderRepository.findAllById(id);
    }

    public boolean deleteOrder(long orderId){
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (!existingOrder.isPresent()){
            message = "Order not found";
            return false;
        }

        detailOrderService.deleteByIdOrder(orderId);
        orderRepository.deleteById(orderId);
        message = "Success";
        current = existingOrder.get();
        return true;
    }
}
