package com.personal.spring_security.service;

import com.personal.spring_security.Dtos.EmployeeDto;
import com.personal.spring_security.EmployeeRepository;
import com.personal.spring_security.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee createEmployee(EmployeeDto employeeDto){

        Employee employee = Employee.builder()
                .name(employeeDto.getName())
                .email(employeeDto.getEmail())
                .dept(employeeDto.getDept())
                .salary(employeeDto.getSalary())
                .build();

        return repository.save(employee);
    }

    public List<Employee> getEmployees(){
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id){
        return repository.findById(id);
    }
}
