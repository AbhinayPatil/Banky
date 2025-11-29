package com.abhinay.bankapp.bankapp.controller;

import com.abhinay.bankapp.bankapp.dto.PaginatedDto;
import com.abhinay.bankapp.bankapp.service.AdminService;
import com.abhinay.bankapp.bankapp.util.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('Admin')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/getUsers")
    public ResponseEntity<ApplicationResponse> getUsers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        ApplicationResponse applicationResponse = new ApplicationResponse();
        try {
            PaginatedDto users = adminService.getAllUsers(pageNumber,pageSize);
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
