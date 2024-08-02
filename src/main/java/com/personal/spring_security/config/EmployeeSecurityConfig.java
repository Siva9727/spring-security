package com.personal.spring_security.config;

import com.personal.spring_security.service.EmployeeUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // to enable role based authorization
public class EmployeeSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails employee = User.withUsername("user")
//                .password(passwordEncoder.encode("user"))  // Encode password
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails hr = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin"))  // Encode password
//                .roles("HR")
//                .build();
//
//        UserDetails admin = User.withUsername("root")
//                .password(passwordEncoder.encode("root"))  // Encode password
//                .roles("EMPLOYEE","HR")
//                .build();
//
//        return new InMemoryUserDetailsManager(employee, admin, hr);

        return new EmployeeUserDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/employees/login","/employees/create").permitAll()  // Unrestricted access
                                .requestMatchers("/employees/**").authenticated()  // Requires authentication
                                .anyRequest().authenticated()  // Any other requests need to be authenticated
                )
                .httpBasic(Customizer.withDefaults());  // Basic authentication

        return http.build();
    }
}