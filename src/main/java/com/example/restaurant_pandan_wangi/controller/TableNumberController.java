package com.example.restaurant_pandan_wangi.controller;

import com.example.restaurant_pandan_wangi.model.ApiResponse;
import com.example.restaurant_pandan_wangi.service.TableNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tables")
public class TableNumberController {
    @Autowired
    TableNumberService tableNumberService;

    @GetMapping("")
    public ResponseEntity getAllTable() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Table.",
                        tableNumberService.getAllTable()
                ));
    }

    @GetMapping("/actived/{isActive}")
    public ResponseEntity getAllTableByActive(@PathVariable boolean isActive) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        (isActive) ? "All list Table active." : "All list Table non-active.",
                        tableNumberService.getAllTableByActive(isActive)
                ));
    }

    @GetMapping("/in-used/{isUse}")
    public ResponseEntity getAllTableByUse(@PathVariable boolean isUse) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        (isUse) ? "List of tables currently in use." : "List of tables not currently in use..",
                        tableNumberService.getAllTableByInUse(isUse)
                ));
    }

    @PostMapping("")
    public ResponseEntity addCourse() {
        tableNumberService.add();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(
                        tableNumberService.getMessage(),
                        tableNumberService.getCurrent()
                ));
    }
}
