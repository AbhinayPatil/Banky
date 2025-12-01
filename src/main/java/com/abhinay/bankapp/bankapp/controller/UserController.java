package com.abhinay.bankapp.bankapp.controller;

import com.abhinay.bankapp.bankapp.dto.AddUserDto;
import com.abhinay.bankapp.bankapp.dto.EditUserDto;
import com.abhinay.bankapp.bankapp.service.UserService;
import com.abhinay.bankapp.bankapp.util.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAuthority('User')")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/edit")
    public ResponseEntity<ApplicationResponse> editUser(@RequestBody EditUserDto editUserDto) {
        return userService.editUser(editUserDto);
    }
}
