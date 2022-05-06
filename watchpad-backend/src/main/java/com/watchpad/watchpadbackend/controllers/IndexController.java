 package com.watchpad.watchpadbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String health() {
        return "Watchpad is running!";  
    }
    
} 
