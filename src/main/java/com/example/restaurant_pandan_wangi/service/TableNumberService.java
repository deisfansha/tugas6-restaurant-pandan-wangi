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
        message = "Table added successfully.";
        tableNumberRepository.save(current);
    }

    public boolean updateInUse(long id, boolean isUsed) {
        Optional<TableNumber> tableNumberOptional = tableNumberRepository.findById(id);

        if (!tableNumberOptional.isPresent()) {
            message = "Table ID Not Found.";
            return false;
        } else {
            tableNumberOptional.get().setTableInUse(isUsed);
            tableNumberRepository.save(tableNumberOptional.get());
            if (isUsed) {
                message = "Table ID `" + id + "` is currently being used.";
            } else {
                message = "Table ID `" + id + "` is available.";
            }
            current = tableNumberOptional.get();
            return true;
        }
    }

    public boolean updateStatus(long id, boolean isActive) {
        Optional<TableNumber> tableNumberOptional = tableNumberRepository.findById(id);

        if (!tableNumberOptional.isPresent()) {
            message = "Table ID Not Found.";
            return false;
        } else {
            tableNumberOptional.get().setActive(isActive);
            if (isActive) {
                message = "Table ID `" + id + "` is now active.";
            } else {
                message = "Table ID `" + id + "` is now inactive.";
                tableNumberOptional.get().setTableInUse(false);
            }
            tableNumberRepository.save(tableNumberOptional.get());
            current = tableNumberOptional.get();
            return true;
        }
    }

    public List<TableNumber> getAllTable() {
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
            message = "Table ID Found.";
            return tableNumberOptional.get();
        } else {
            message = "Table ID Not Found.";
            return null;
        }
    }
}
