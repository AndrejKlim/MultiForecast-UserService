package com.multiforecast.userservice.mapper;

import com.multiforecast.userservice.persistance.entity.User;
import com.multiforecast.userservice.service.dto.UserDTO;

public class UserMapper {
    private UserMapper() {
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getUserId(), LocationMapper.toDTO(user.getLocation()));
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.userId());
        user.setLocation(LocationMapper.toEntity(userDTO.location()));
        return user;
    }
}
