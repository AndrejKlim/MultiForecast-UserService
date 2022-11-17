package com.multiforecast.userservice.service;

import com.multiforecast.userservice.mapper.LocationMapper;
import com.multiforecast.userservice.persistance.entity.UserEntity;
import com.multiforecast.userservice.persistance.repo.UserRepo;
import com.multiforecast.userservice.service.dto.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final UserRepo userRepo;

    public Location getLocation(Long userId) {
        return userRepo.findById(userId).map(UserEntity::getLocation).map(LocationMapper::toDTO).orElse(null);
    }
}
