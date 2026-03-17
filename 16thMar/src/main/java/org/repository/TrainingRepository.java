package org.repository;

import org.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate today1, LocalDate today2);

    List<Training> findByStartDateGreaterThan(LocalDate today);

    List<Training> findByTopicContainingIgnoreCaseAndStartDateGreaterThanEqual(
            String topic, LocalDate today);

}