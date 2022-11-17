package com.multiforecast.userservice.persistance.repo;

import com.multiforecast.userservice.persistance.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<LocationEntity, Long> {
}
