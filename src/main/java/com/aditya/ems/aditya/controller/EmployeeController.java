package com.aditya.ems.aditya.controller;

import com.aditya.ems.aditya.dto.EmployeeDto;
import com.aditya.ems.aditya.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee =  employeeService.createEmployee(employeeDto);
        return  new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long empid) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(empid);
        return ResponseEntity.ok(employeeDto);
    }
    
    @GetMapping("")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDtos);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updatedEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto updatedEmployee) {
        EmployeeDto employeeDto = employeeService.updateEmployee(id,updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empid) {
        employeeService.deleteEmployee(empid);
        return ResponseEntity.ok("Employee with id :" +empid+" is successfully delelted!!");
    }
}
