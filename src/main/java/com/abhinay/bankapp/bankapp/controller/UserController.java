package com.abhinay.bankapp.bankapp.controller;

import com.abhinay.bankapp.bankapp.dto.GetUsersDto;
import com.abhinay.bankapp.bankapp.dto.AddUserDto;
import com.abhinay.bankapp.bankapp.service.UserService;
import com.abhinay.bankapp.bankapp.util.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUsers")
    public ResponseEntity<ApplicationResponse> login(@RequestBody AddUserDto input) {
        ApplicationResponse applicationResponse = new ApplicationResponse();
        userService.addUser(input);
        applicationResponse.setMessage("User added successfully");
        applicationResponse.setStatus("SUCCESS");
        return ResponseEntity.ok(applicationResponse);
    }

    @PostMapping("/getUsers")
    public ResponseEntity<ApplicationResponse> getUsers() {
        ApplicationResponse applicationResponse = new ApplicationResponse();
        try {
            List<GetUsersDto> users = userService.getAllUsers();
            applicationResponse.setStatus("SUCCESS");
            applicationResponse.setMessage("Users fetched successfully");
            applicationResponse.setData(users);
        } catch (Exception e) {
            System.out.println("Error fetching users: " + e.getMessage());
            applicationResponse.setStatus("FAIL");
            applicationResponse.setMessage("Error fetching users: " + e.getMessage());
        }
        return ResponseEntity.ok(applicationResponse);
    }
}