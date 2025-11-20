package com.abhinay.bankapp.bankapp.service;

import com.abhinay.bankapp.bankapp.dto.AddUserDto;
import com.abhinay.bankapp.bankapp.dto.GetUsersDto;
import com.abhinay.bankapp.bankapp.dto.PaginatedDto;
import com.abhinay.bankapp.bankapp.util.CustomDuplicatePhoneException;
import com.abhinay.bankapp.bankapp.util.SecurePasswordHasher;
import com.abhinay.bankapp.bankapp.util.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

import com.abhinay.bankapp.bankapp.repository.UserRepository;
import com.abhinay.bankapp.bankapp.entity.Users;

@Service
public class UserService {

    private final SecurePasswordHasher securePasswordHasher;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(SecurePasswordHasher securePasswordHasher, UserRepository userRepository, UserMapper userMapper) {
        this.securePasswordHasher = securePasswordHasher;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public PaginatedDto getAllUsers(int pageNumber, int pageSize) throws Exception {
        PaginatedDto paginatedDto = new PaginatedDto();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Users> users = userRepository.findAll(pageable);

        List<GetUsersDto> getUsersDto = userMapper.toDto(users.getContent());
        int totalPages = users.getTotalPages();
        long totalElements = users.getTotalElements();

        paginatedDto.setData(getUsersDto);
        paginatedDto.setTotalPages(totalPages);
        paginatedDto.setTotalElements(totalElements);
        paginatedDto.setCurrentPage(pageNumber);

        return paginatedDto;
    }

    @Transactional
    public void addUser(AddUserDto input) {
        try {
            //sha encryption logic for password
            String pass = input.getPassword();
            input.setPassword(securePasswordHasher.getPassword(pass));
            userRepository.save(userMapper.toEntity(input));
        }
        catch (DataIntegrityViolationException e) {
            throw new CustomDuplicatePhoneException("Phone Number already exists");
        }
    }
}
