package com.aditya.ems.aditya.service.impl;

import com.aditya.ems.aditya.dto.EmployeeDto;
import com.aditya.ems.aditya.entity.Employee;
import com.aditya.ems.aditya.exception.ResourceNotFoundException;
import com.aditya.ems.aditya.mapper.EmployeeMapper;
import com.aditya.ems.aditya.repository.EmployeeRepository;
import com.aditya.ems.aditya.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not exists in the Database with id : "+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee not exists in the Database with id : "+employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee not exists in the Database with id : "+employeeId));

        employeeRepository.deleteById(employeeId);

    }
}
