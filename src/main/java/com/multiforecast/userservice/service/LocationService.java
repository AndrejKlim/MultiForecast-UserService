package com.multiforecast.userservice.service;

import com.multiforecast.userservice.mapper.LocationMapper;
import com.multiforecast.userservice.persistance.entity.User;
import com.multiforecast.userservice.persistance.repo.UserRepo;
import com.multiforecast.userservice.service.dto.LocationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final UserRepo userRepo;

    public LocationDTO getLocation(Long userId) {
        return userRepo.findById(userId).map(User::getLocation).map(LocationMapper::toDTO).orElse(null);
    }
}
