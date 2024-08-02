package com.personal.spring_security.service;

import com.personal.spring_security.Dtos.EmployeeDto;
import com.personal.spring_security.EmployeeRepository;
import com.personal.spring_security.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final String DEFAULT_ROLE = "ROLE_EMPLOYEE";
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee createEmployee(EmployeeDto employeeDto){

        Employee employee = Employee.builder()
                .name(employeeDto.getName())
                .email(employeeDto.getEmail())
                .password(passwordEncoder.encode(employeeDto.getPassword()))
                .dept(employeeDto.getDept())
                .username(employeeDto.getUsername())
                .salary(employeeDto.getSalary())
                .roles(DEFAULT_ROLE)
                .build();
        return repository.save(employee);
    }

    public List<Employee> getEmployees(){
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id){
        return repository.findById(id);
    }

    public Employee changeRole(Employee employee){
        Employee existing = repository.findById(employee.getId()).orElseThrow(()-> new RuntimeException("invalid id"));
        existing.setRoles(employee.getRoles());
        return repository.save(existing);
    }
}
