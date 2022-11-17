package com.multiforecast.userservice.mapper;

import com.multiforecast.userservice.persistance.entity.UserEntity;
import com.multiforecast.userservice.service.dto.User;

public class UserMapper {
    private UserMapper() {
    }

    public static User toDTO(UserEntity userEntity) {
        return new User(userEntity.getUserId(), LocationMapper.toDTO(userEntity.getLocation()));
    }

    public static UserEntity toEntity(User userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDTO.userId());
        userEntity.setLocation(LocationMapper.toEntity(userDTO.location()));
        return userEntity;
    }
}
