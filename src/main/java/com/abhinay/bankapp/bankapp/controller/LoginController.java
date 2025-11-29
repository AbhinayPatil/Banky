package com.abhinay.bankapp.bankapp.controller;

import com.abhinay.bankapp.bankapp.dto.LoginReqDto;
import com.abhinay.bankapp.bankapp.dto.JwtResponse;
import com.abhinay.bankapp.bankapp.entity.Users;
import com.abhinay.bankapp.bankapp.repository.UserRepository;
import com.abhinay.bankapp.bankapp.util.JwtUtil;
import com.abhinay.bankapp.bankapp.util.SecurePasswordHasher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final UserRepository userRepository;
    private final SecurePasswordHasher securePasswordHasher;
    private final JwtUtil jwtUtil;

    public LoginController(UserRepository userRepository,
                          SecurePasswordHasher securePasswordHasher,
                          JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.securePasswordHasher = securePasswordHasher;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto req) {
        if (req.getUsername() == null || req.getPassword() == null) {
            return ResponseEntity.badRequest().body("username and password required");
        }

        Optional<Users> maybeUser = userRepository.findByEmail(req.getUsername());
        if (maybeUser.isEmpty()) {
            return ResponseEntity.status(401).body("invalid credentials");
        }
        Users user = maybeUser.get();
        boolean ok = securePasswordHasher.verifyPassword(req.getPassword(), user.getPassword());
        if (!ok) {
            return ResponseEntity.status(401).body("invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
