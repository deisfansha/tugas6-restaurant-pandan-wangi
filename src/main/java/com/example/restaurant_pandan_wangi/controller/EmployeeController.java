package com.example.restaurant_pandan_wangi.controller;

import com.example.restaurant_pandan_wangi.model.ApiResponse;
import com.example.restaurant_pandan_wangi.model.Employee;
import com.example.restaurant_pandan_wangi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("")
    public ResponseEntity createdEmployee(@RequestBody Employee employeeRequest){
        boolean added = employeeService.create(employeeRequest);
        if (added){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(employeeService.getMessage(), employeeRequest));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(employeeService.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity getAllEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeService.getAll()));
    }

    @GetMapping("/actived")
    public ResponseEntity getAllActivedEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeService.getAllByIsActive()));
    }

    @GetMapping("/{position}")
    public ResponseEntity getAllPositionEmployee(@PathVariable boolean position){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeService.getAllByPosition(position)));
    }

    @PutMapping("/{id}")
    public ResponseEntity updatedEmployee(@PathVariable Long id, @RequestBody Employee employeeRequest){
        boolean updated = employeeService.update(id,employeeRequest);
        if (updated){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(employeeService.getMessage(), employeeService.getCurrent()));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(employeeService.getMessage()));
        }
    }

    @PatchMapping("/actives/{id}")
    public ResponseEntity updatedActiveEmployee(@PathVariable Long id, @RequestBody Employee employeeRequest){
        boolean updated = employeeService.updateActived(id,employeeRequest);
        if (updated){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(employeeService.getMessage(), employeeService.getCurrent()));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(employeeService.getMessage()));
        }
    }

}
