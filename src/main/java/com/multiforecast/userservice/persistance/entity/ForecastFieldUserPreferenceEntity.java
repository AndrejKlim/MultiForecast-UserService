package com.multiforecast.userservice.persistance.entity;

import com.multiforecast.userservice.enums.Field;
import com.multiforecast.userservice.enums.Source;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "forecast_field_user_preferences")
public class ForecastFieldUserPreferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Source source;
    @Enumerated(EnumType.STRING)
    private Field field;
    @Column(name = "search_path")
    private String searchPath;

    @ManyToMany(mappedBy = "forecastFieldPreferenceList")
    private List<UserEntity> users;

}
