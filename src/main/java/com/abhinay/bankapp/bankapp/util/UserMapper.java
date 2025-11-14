package com.abhinay.bankapp.bankapp.util;

import com.abhinay.bankapp.bankapp.dto.GetUsersDto;
import com.abhinay.bankapp.bankapp.entity.Users;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    GetUsersDto toDto(Users users);
    List<GetUsersDto> toDto(List<Users> users);
}
