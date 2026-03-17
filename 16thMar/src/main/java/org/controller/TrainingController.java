package org.controller;

import org.entity.Training;
import org.services.TrainingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainingController {

    private TrainingService service;

    public TrainingController(TrainingService service){
        this.service = service;
    }

    @GetMapping("/trainings")
    public List<Training> getTrainings(
            @RequestParam(required=false) String name){

        if(name==null){
            return service.getCurrentAndUpcoming();
        }

        return service.searchCurrentUpcoming(name);
    }

    @GetMapping("/trainings/upcoming")
    public List<Training> getUpcoming(@RequestParam String name){

        return service.searchCurrentUpcoming(name);
    }
}