package com.abhinay.bankapp.bankapp.controller;

import com.abhinay.bankapp.bankapp.entity.Users;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody Users input) {
        return "Login successful!";
    }
}
