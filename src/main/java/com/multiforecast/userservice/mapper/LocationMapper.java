package com.multiforecast.userservice.mapper;


import com.multiforecast.userservice.persistance.entity.LocationEntity;
import com.multiforecast.userservice.service.dto.Location;

public class LocationMapper {
    private LocationMapper() {
    }

    public static Location toDTO(LocationEntity locationEntity) {
        return new Location(locationEntity.getLocationId(), locationEntity.getLon(), locationEntity.getLat());
    }

    public static LocationEntity toEntity(Location locationDTO) {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setLocationId(locationDTO.locationId());
        locationEntity.setLat(locationDTO.lat());
        locationEntity.setLon(locationDTO.lon());
        return locationEntity;
    }
}
