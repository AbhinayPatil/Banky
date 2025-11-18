package com.abhinay.bankapp.bankapp.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle custom duplicate phone exception
    @ExceptionHandler(CustomDuplicatePhoneException.class)
    public ResponseEntity<ApplicationResponse> handleDuplicatePhone(CustomDuplicatePhoneException ex) {
        System.out.println("DataIntegrity Exception: " + ex.getMessage());
        ApplicationResponse response = new ApplicationResponse();
        response.setMessage("Phone number already exists");
        response.setStatus("FAIL");
        return ResponseEntity.status(409).body(response); // 409 Conflict
    }

    // Handle any other unexpected exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApplicationResponse> handleGenericException(Exception ex) {
        ApplicationResponse response = new ApplicationResponse();
        System.out.println("Something went wrong: " + ex.getMessage());
        response.setMessage("Something went wrong");
        response.setStatus("FAIL");
        return ResponseEntity.status(500).body(response); // 500 Internal Server Error
    }
}

