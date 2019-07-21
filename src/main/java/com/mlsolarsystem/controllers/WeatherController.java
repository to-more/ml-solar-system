package com.mlsolarsystem.controllers;

import com.mlsolarsystem.services.WeatherService;
import com.mlsolarsystem.models.Weather;
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
@RequestMapping("/weathers")
public class WeatherController {

    private static final String GET_WEATHER_BY_DAY = "Get weather by day";
    private static final String GET_ALL_WEATHERS = "Get all weathers";
    private static final String CREATE_WEATHER = "Create weather";
    private static final String DELETE_ALL_WEATHERS = "Delete all weathers";
    private static final String DELETED_ALL = "Deleted All";
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("{day}")
    public ResponseEntity<Weather> getWeatherAt(@PathVariable(value = "day", required = false) Integer day){
        return LoggerUtils.benchmark(
                GET_WEATHER_BY_DAY,
            () -> new ResponseEntity<>(weatherService.getWeatherAt(day), HttpStatus.OK)
        );
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getWeathers(){
        return LoggerUtils.benchmark(
            GET_ALL_WEATHERS,
            () -> new ResponseEntity<>(weatherService.getAll(), HttpStatus.OK)
        );
    }

    @PostMapping
    public ResponseEntity<Weather> postWeatherAt(@RequestBody Weather weather){
        return LoggerUtils.benchmark(
            CREATE_WEATHER,
            () -> new ResponseEntity<>(weatherService.saveWeatherAt(weather), HttpStatus.CREATED)
        );
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll(){
        return LoggerUtils.benchmark(
            DELETE_ALL_WEATHERS,
            () -> {
                weatherService.deleteAll();
                return new ResponseEntity<>(DELETED_ALL, HttpStatus.OK);
            }
        );
    }
}
