package com.example.restaurant_pandan_wangi.service;

import com.example.restaurant_pandan_wangi.model.TableNumber;
import com.example.restaurant_pandan_wangi.repository.TableNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableNumberService {
    @Autowired
    TableNumberRepository tableNumberRepository;
    private TableNumber current;
    private String message;

    public TableNumber getCurrent() {
        return current;
    }

    public String getMessage() {
        return message;
    }

    public void add() {
        current = new TableNumber();
        message = "Table added successfully.";
        tableNumberRepository.save(current);
    }

    public List<TableNumber> getAllTable() {
        return tableNumberRepository.findAllTable();
    }

    public List<TableNumber> getAllTableByInUse(boolean isUse) {
        return tableNumberRepository.findAllTableByInUse(isUse);
    }

    public List<TableNumber> getAllTableByActive(boolean isActive) {
        return tableNumberRepository.findAllTableByActive(isActive);
    }
}
