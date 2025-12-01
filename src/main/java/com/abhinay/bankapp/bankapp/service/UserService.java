package com.abhinay.bankapp.bankapp.service;

import com.abhinay.bankapp.bankapp.dto.AddUserDto;
import com.abhinay.bankapp.bankapp.dto.EditUserDto;
import com.abhinay.bankapp.bankapp.repository.AccountRepository;
import com.abhinay.bankapp.bankapp.util.AccountMapper;
import com.abhinay.bankapp.bankapp.util.ApplicationResponse;
import com.abhinay.bankapp.bankapp.util.CustomDuplicatePhoneException;
import com.abhinay.bankapp.bankapp.util.SecurePasswordHasher;
import com.abhinay.bankapp.bankapp.util.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.abhinay.bankapp.bankapp.repository.UserRepository;
import com.abhinay.bankapp.bankapp.entity.Users;

@Service
public class UserService {

    private final SecurePasswordHasher securePasswordHasher;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public UserService(SecurePasswordHasher securePasswordHasher, UserRepository userRepository, UserMapper userMapper, AccountRepository accountRepository, AccountMapper accountMapper) {
        this.securePasswordHasher = securePasswordHasher;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }



    @Transactional
    public void addUser(AddUserDto input) {
        try {
            //sha encryption logic for password
            String pass = input.getPassword();
            input.setPassword(securePasswordHasher.getPassword(pass));
            Users user = userRepository.save(userMapper.toEntity(input));
            accountRepository.save(accountMapper.toEntity(input));

        }
        catch (DataIntegrityViolationException e) {
            throw new CustomDuplicatePhoneException("Phone Number already exists");
        }
    }

    @Transactional
    public ResponseEntity<ApplicationResponse> editUser(EditUserDto editUserDto) {
        Optional<Users> optionalUser = userRepository.findByEmail(editUserDto.getEmail());
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setName(editUserDto.getName());
            user.setPhoneNumber(editUserDto.getPhoneNumber());
            userRepository.save(user);
            return ResponseEntity.ok(new ApplicationResponse("User updated successfully", "SUCCESS", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApplicationResponse("User not found", "FAIL", null));
        }
    }
}
