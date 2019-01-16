package com.teletorflix.app.repository;

import com.teletorflix.app.model.ScheduleDay;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ScheduleDayRepositoryIntegrationTest {

    @Autowired
    private ScheduleDayRepository scheduleDayRepository;

    @Test
    void saveScheduleDay_validScheduleDay_returnsScheduleDay() {
        ScheduleDay expected = ScheduleDay.of("Monday");
        ScheduleDay saved = scheduleDayRepository.save(expected);
        expected.setId(1);
        assertThat(saved).isEqualTo(expected);
    }

    @Test
    void saveScheduleDay_DuplicateScheduleDay_returnsSameEntity() {
        ScheduleDay expected = ScheduleDay.of("Monday");
        ScheduleDay saved = scheduleDayRepository.save(expected);
        ScheduleDay duplicateSaved = scheduleDayRepository.save(expected);

        assertThat(duplicateSaved).isEqualTo(saved);
    }
}