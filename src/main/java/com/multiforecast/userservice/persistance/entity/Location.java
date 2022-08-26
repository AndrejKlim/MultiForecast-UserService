package com.multiforecast.userservice.persistance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Getter
@Setter
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "lon")
    private Float lon;

    @Column(name = "lat")
    private Float lat;

    public Location(Float lon, Float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Location() {
    }
}
