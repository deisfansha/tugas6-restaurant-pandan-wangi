package com.example.restaurant_pandan_wangi.controller;

import com.example.restaurant_pandan_wangi.model.ApiResponse;
import com.example.restaurant_pandan_wangi.model.Menu;
import com.example.restaurant_pandan_wangi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @PostMapping("")
    public ResponseEntity addCourse(@RequestBody Menu menuRequest) {
        if (menuService.add(menuRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(
                            menuService.getMessage(),
                            menuRequest
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            menuService.getMessage()
                    ));
        }
    }

}
