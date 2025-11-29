package com.abhinay.bankapp.bankapp.service;

import com.abhinay.bankapp.bankapp.dto.GetUsersDto;
import com.abhinay.bankapp.bankapp.dto.PaginatedDto;
import com.abhinay.bankapp.bankapp.entity.Users;
import com.abhinay.bankapp.bankapp.repository.UserRepository;
import com.abhinay.bankapp.bankapp.util.SecurePasswordHasher;
import com.abhinay.bankapp.bankapp.util.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AdminService(SecurePasswordHasher securePasswordHasher, UserRepository userRepository, UserMapper userMapper) {
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
}
