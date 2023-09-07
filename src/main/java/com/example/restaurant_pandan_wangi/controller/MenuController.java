package com.example.restaurant_pandan_wangi.controller;

import com.example.restaurant_pandan_wangi.model.ApiResponse;
import com.example.restaurant_pandan_wangi.model.Menu;
import com.example.restaurant_pandan_wangi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("")
    public ResponseEntity getAllMenu() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Menu.",
                        menuService.getAllMenu()
                ));
    }

    @GetMapping("/active-menu")
    public ResponseEntity getAllMenuActived() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "All list Menu active.",
                        menuService.getAllMenuActived()
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity getMenuById(@PathVariable long id) {
        if (menuService.getMenuById(id) != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(
                            menuService.getMessage(),
                            menuService.getMenuById(id)
                    ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(
                            menuService.getMessage()
                    ));
        }
    }

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
