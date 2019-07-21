package com.mlsolarsystem.controllers;

import com.mlsolarsystem.models.Prediction;
import com.mlsolarsystem.services.GalaxyService;
import com.mlsolarsystem.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tom
 */
@RestController
@RequestMapping("/predictions")
public class PredictionController {

    private static final String GET_PREDICTIONS_AT_RANGE_OF_DAYS = "Get predictions at range of days";
    private final GalaxyService galaxyService;

    @Autowired
    public PredictionController(GalaxyService galaxyService) {
        this.galaxyService = galaxyService;
    }


    @GetMapping
    public ResponseEntity<Prediction> getPredictions(
        @RequestParam("startDay") Integer startDay,
        @RequestParam("endDay") Integer endDay){

        return LoggerUtils.benchmark(
            GET_PREDICTIONS_AT_RANGE_OF_DAYS,
            ()-> new ResponseEntity<>(galaxyService.predictWeatherAtRange(startDay, endDay), HttpStatus.OK)
        );
    }

    @PostMapping
    public ResponseEntity<String> createPredictions(
        @RequestParam("startDay") Integer startDay,
        @RequestParam("endDay") Integer endDay){

        return LoggerUtils.benchmark(
            GET_PREDICTIONS_AT_RANGE_OF_DAYS,
            ()-> {
                galaxyService.createPlanetModel(startDay, endDay);
                return new ResponseEntity<>(
                    "Crated predictions",
                    HttpStatus.OK
                );
            }
        );
    }
}
