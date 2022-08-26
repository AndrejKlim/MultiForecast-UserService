package com.multiforecast.userservice.mapper;


import com.multiforecast.userservice.persistance.entity.Location;
import com.multiforecast.userservice.service.dto.LocationDTO;

public class LocationMapper {
    private LocationMapper() {
    }

    public static LocationDTO toDTO(Location location) {
        return new LocationDTO(location.getLocationId(), location.getLon(), location.getLat());
    }

    public static Location toEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setLocationId(locationDTO.locationId());
        location.setLat(locationDTO.lat());
        location.setLon(locationDTO.lon());
        return location;
    }
}
