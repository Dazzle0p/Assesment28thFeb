package org.services;

import org.entity.Training;
import org.repository.TrainingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TrainingService {

    private TrainingRepository repo;

    public TrainingService(TrainingRepository repo){
        this.repo = repo;
    }

    public List<Training> getCurrentAndUpcoming() {
        LocalDate today = LocalDate.now();

        List<Training> ongoing =
                repo.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(today,today);

        List<Training> upcoming =
                repo.findByStartDateGreaterThan(today);

        ongoing.addAll(upcoming);
        return ongoing;
    }

    public List<Training> searchCurrentUpcoming(String topic){
        LocalDate today = LocalDate.now();
        return repo.findByTopicContainingIgnoreCaseAndStartDateGreaterThanEqual(topic,today);
    }

}