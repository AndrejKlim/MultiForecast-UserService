package com.multiforecast.userservice.persistance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

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
    private LocationEntity location;

    public UserEntity(Long userId, LocationEntity locationEntity) {
        this.userId = userId;
        this.location = locationEntity;
    }

    public UserEntity() {
    }
}