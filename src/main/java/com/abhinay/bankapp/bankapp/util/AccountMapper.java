package com.abhinay.bankapp.bankapp.util;

import com.abhinay.bankapp.bankapp.dto.AddUserDto;
import com.abhinay.bankapp.bankapp.entity.Account;
import com.abhinay.bankapp.bankapp.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AddUserDto input);
}
