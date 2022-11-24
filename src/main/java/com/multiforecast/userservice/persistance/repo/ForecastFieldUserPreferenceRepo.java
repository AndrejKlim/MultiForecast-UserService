package com.multiforecast.userservice.persistance.repo;

import com.multiforecast.userservice.enums.Field;
import com.multiforecast.userservice.persistance.entity.ForecastFieldUserPreferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ForecastFieldUserPreferenceRepo extends JpaRepository<ForecastFieldUserPreferenceEntity, Long> {

    List<ForecastFieldUserPreferenceEntity> findByFieldIn(List<Field> fields);

    @Query(value = """
                SELECT jsonb_merge_recurse_all(ARRAY(SELECT CAST(search_path AS JSONB)
                                                 FROM forecast_field_user_preferences ffup
                                                          JOIN user_field_preferences ufp ON ufp.pref_id = ffup.id
                                                          JOIN users u ON ufp.user_id = u.user_id
                                                 WHERE u.user_id = 381058662));""",
        nativeQuery = true)
    Optional<String> getSearchPathForUser(Long userId);
}
