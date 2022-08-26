package com.multiforecast.userservice.persistance.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @ManyToOne()
    @JoinColumn(name = "location_id")
    private Location location;

    public User(Long userId, Location location) {
        this.userId = userId;
        this.location = location;
    }

    public User() {
    }
}