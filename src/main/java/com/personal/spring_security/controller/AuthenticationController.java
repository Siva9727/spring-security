package com.personal.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping("/home")
    public String home(){
        return "Welcome to the page";
    }

    @GetMapping("/greet")
    public String greet(){
        return "Hi How are you";
    }

    @GetMapping("/text")
    public String noSecure(){
        return "Welcome to the page";
    }
}
