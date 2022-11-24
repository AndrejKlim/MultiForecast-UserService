package com.multiforecast.userservice.persistance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;
    @ManyToOne()
    @JoinColumn(name = "location_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @ToString.Exclude
    private LocationEntity location;
    
    @ManyToMany
    @JoinTable(
            name = "user_field_preferences",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "pref_id"))
    @ToString.Exclude
    private List<ForecastFieldUserPreferenceEntity> forecastFieldPreferenceList;

    public UserEntity() {
    }
}