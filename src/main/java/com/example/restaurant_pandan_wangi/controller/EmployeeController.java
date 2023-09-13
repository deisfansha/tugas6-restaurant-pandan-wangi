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

    // API untuk menambahkan Employee baru berdasarkan request.
    @PostMapping("")
    public ResponseEntity createdEmployee(@RequestBody Employee employeeRequest){
        boolean added = employeeService.create(employeeRequest);
        if (added){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeRequest));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(employeeService.getMessage()));
        }
    }

    // API untuk menampilkan semua daftar Employee.
    @GetMapping("")
    public ResponseEntity getAllEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeService.getAll()));
    }

    // API untuk menampilkan informasi Employee berdasarkan ID Employee.
    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable long id){
        if (employeeService.getById(id) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(employeeService.getMessage()));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeService.getById(id)));
        }
    }

    // API untuk menampilkan daftar Employee berdasarkan yang masih bekerja.
    @GetMapping("/actived")
    public ResponseEntity getAllActivedEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeService.getAllByIsActive()));
    }

    // API untuk menampilkan daftar Employee berdasarkan poisisi.
    @GetMapping("/position/{position}")
    public ResponseEntity getAllPositionEmployee(@PathVariable boolean position){
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeService.getAllByPosition(position)));
    }

    // API untuk memperbarui informasi Employee berdasarkan ID Employee dan request.
    @PutMapping("/{id}")
    public ResponseEntity updatedEmployee(@PathVariable long id, @RequestBody Employee employeeRequest){
        boolean updated = employeeService.update(id,employeeRequest);
        if (updated){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeService.getById(id)));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(employeeService.getMessage()));
        }
    }

    // API untuk memperbarui status bekerja Employee berdasarkan ID Employee.
    @PatchMapping("/actives/{id}")
    public ResponseEntity updatedActiveEmployee(@PathVariable long id, @RequestBody Employee employeeRequest){
        boolean updated = employeeService.updateActivated(id,employeeRequest);
        if (updated){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Success", employeeService.getById(id)));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(employeeService.getMessage()));
        }
    }

}
