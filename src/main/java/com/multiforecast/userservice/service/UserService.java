package com.multiforecast.userservice.service;

import com.multiforecast.userservice.enums.Field;
import com.multiforecast.userservice.persistance.entity.ForecastFieldUserPreferenceEntity;
import com.multiforecast.userservice.persistance.entity.LocationEntity;
import com.multiforecast.userservice.persistance.entity.UserEntity;
import com.multiforecast.userservice.persistance.repo.ForecastFieldUserPreferenceRepo;
import com.multiforecast.userservice.persistance.repo.LocationRepo;
import com.multiforecast.userservice.persistance.repo.UserRepo;
import com.multiforecast.userservice.service.dto.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final LocationRepo locationRepo;
    private final ForecastFieldUserPreferenceRepo userPreferenceRepo;

    public boolean userExists(Long userId) {
        return userRepo.existsById(userId);
    }

    @Transactional
    public void saveOrUpdateLocation(final long userId, final Location location) {
        Optional<UserEntity> optionalUser = userRepo.findById(userId);

        UserEntity userEntity;
        if (optionalUser.isPresent()) {
            log.info("Updating user {} with location {}", userId, location);
            userEntity = optionalUser.get();
        } else {
            log.info("Creating new user {} with location {}", userId, location);
            userEntity = new UserEntity();
            userEntity.setUserId(userId);
        }
        LocationEntity locationEntity = new LocationEntity(location.lon(), location.lat());
        locationRepo.save(locationEntity);
        userEntity.setLocation(locationEntity);
        userRepo.save(userEntity);
    }

    @Transactional
    public void saveFieldPrefs(final long userId, final List<Field> fieldsList) {
        Optional<UserEntity> optionalUser = userRepo.findById(userId);
        List<ForecastFieldUserPreferenceEntity> fields = userPreferenceRepo.findByFieldIn(fieldsList);

        if (fields.isEmpty()) {
            log.warn("No field prefs to save");
            return;
        }

        UserEntity userEntity;
        if (optionalUser.isPresent()) {
            userEntity = optionalUser.get();
            userEntity.setForecastFieldPreferenceList(fields);
            userRepo.save(userEntity);
        } else {
            log.warn("No User found with id = {}", userId);
        }
    }

    public String getUserSearchPath(final long userId) {
        return userPreferenceRepo.getSearchPathForUser(userId).orElse("");
    }
}
