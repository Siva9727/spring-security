package com.personal.spring_security.controller;

import com.personal.spring_security.Dtos.EmployeeDto;
import com.personal.spring_security.entity.Employee;
import com.personal.spring_security.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/login")
    public String welcome(){
        return "Welcome to the the employee registration !";
    }

    @PostMapping("/create")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Employee createEmployee(@RequestBody EmployeeDto employee){
        return service.createEmployee(employee);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_HR')")
    public List<Employee> getAll(){
        return service.getEmployees();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public Optional<Employee> getEmployeeDetails(@PathVariable Integer id){
        return service.getEmployeeById(id);
    }

    @PutMapping("/change")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public Employee changeRoles(Employee employee){
        return service.changeRole(employee);
    }
}
