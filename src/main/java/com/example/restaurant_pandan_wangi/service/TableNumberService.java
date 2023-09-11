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
    private TableNumberRepository tableNumberRepository;
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
        tableNumberRepository.save(current);
    }

    public boolean updateInUse(long id, boolean isUsed) {
        Optional<TableNumber> tableNumberOptional = tableNumberRepository.findById(id);

        if (!tableNumberOptional.isPresent()) {
            message = "Table Not Found";
            return false;
        } else {
            tableNumberOptional.get().setTableInUse(isUsed);
            tableNumberRepository.save(tableNumberOptional.get());
            current = tableNumberOptional.get();
            return true;
        }
    }

    public boolean updateStatus(long id, boolean isActive) {
        Optional<TableNumber> tableNumberOptional = tableNumberRepository.findById(id);

        if (!tableNumberOptional.isPresent()) {
            message = "Table Not Found";
            return false;
        } else {
            tableNumberOptional.get().setActive(isActive);
            if (!isActive) {
                tableNumberOptional.get().setTableInUse(false);
            }
            tableNumberRepository.save(tableNumberOptional.get());
            current = tableNumberOptional.get();
            return true;
        }
    }

    public List<TableNumber> getAllTable() {
        if (tableNumberRepository.count() == 0) seed();
        return tableNumberRepository.findAllTable();
    }

    public List<TableNumber> getAllTableByInUse(boolean isUsed) {
        return tableNumberRepository.findAllTableByInUse(isUsed);
    }

    public List<TableNumber> getAllTableByActive(boolean isActive) {
        return tableNumberRepository.findAllTableByActive(isActive);
    }

    public TableNumber getTableById(long id) {
        Optional<TableNumber> tableNumberOptional = tableNumberRepository.findById(id);

        if (tableNumberOptional.isPresent()) {
            return tableNumberOptional.get();
        } else {
            message = "Table Not Found";
            return null;
        }
    }

    private void seed() {
        tableNumberRepository.save(new TableNumber());
        tableNumberRepository.save(new TableNumber());
        tableNumberRepository.save(new TableNumber());
        tableNumberRepository.save(new TableNumber());
        tableNumberRepository.save(new TableNumber());
    }
}
