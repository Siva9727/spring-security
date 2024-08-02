package com.personal.spring_security.service;

import com.personal.spring_security.EmployeeRepository;
import com.personal.spring_security.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class EmployeeUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = repository.findByUsername(username);

        return employee.map(EmployeeUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }
}
