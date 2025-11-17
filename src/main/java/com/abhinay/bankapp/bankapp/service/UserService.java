package com.abhinay.bankapp.bankapp.service;

import com.abhinay.bankapp.bankapp.dto.AddUserDto;
import com.abhinay.bankapp.bankapp.dto.GetUsersDto;
import com.abhinay.bankapp.bankapp.util.CustomDuplicatePhoneException;
import com.abhinay.bankapp.bankapp.util.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.abhinay.bankapp.bankapp.repository.UserRepository;
import com.abhinay.bankapp.bankapp.entity.Users;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public List<GetUsersDto> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return userMapper.toDto(users);
    }

    @Transactional
    public void addUser(AddUserDto input) {
        try {
            Users savedUser = userRepository.save(userMapper.toEntity(input));
        }
        catch (DataIntegrityViolationException e) {
            throw new CustomDuplicatePhoneException("Phone Number already exists");
        }
    }
}
