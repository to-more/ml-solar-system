package com.mlsolarsystem.controllers;

import com.mlsolarsystem.models.Planet;
import com.mlsolarsystem.repository.GalaxyRepository;
import com.mlsolarsystem.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tom
 */
@RestController
@RequestMapping("/planets")
public class PlanetsController {

    private static final String CREATE_PLANET = "Planet creation";
    private static final String GET_ALL_PLANET = "Find all planets";
    private static final String GET_PLANET = "Get planet";
    private static final String DELETED_ALL_PLANET = "Deleting all planets";
    private final GalaxyRepository galaxyRepository;

    @Autowired
    public PlanetsController(GalaxyRepository galaxyRepository) {
        this.galaxyRepository = galaxyRepository;
    }

    @PostMapping
    public ResponseEntity<Planet> postPlanet(@RequestBody Planet planet){
        return LoggerUtils.benchmark(
            CREATE_PLANET,
            () -> new ResponseEntity<>(galaxyRepository.save(planet), HttpStatus.CREATED)
        );
    }

    @GetMapping("{name}")
    public ResponseEntity<Planet> getPlanet(@PathVariable(value = "name", required = false) String name){
        return LoggerUtils.benchmark(
            GET_PLANET,
            () -> new ResponseEntity<>(galaxyRepository.findById(name).orElse(new Planet()), HttpStatus.OK)
        );
    }

    @GetMapping
    public ResponseEntity<List<Planet>> getAll(){
        return LoggerUtils.benchmark(
            GET_ALL_PLANET,
            () -> new ResponseEntity<>(galaxyRepository.findAll(), HttpStatus.OK)
        );
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll(){
        return LoggerUtils.benchmark(
            DELETED_ALL_PLANET,
            () -> {
                galaxyRepository.deleteAll();
                return new ResponseEntity<>("Deleted all planets", HttpStatus.OK);
            }
        );
    }
}
