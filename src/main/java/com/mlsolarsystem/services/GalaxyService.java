package com.mlsolarsystem.services;

import com.mlsolarsystem.models.Galaxy;
import com.mlsolarsystem.models.Planet;
import com.mlsolarsystem.models.Prediction;
import com.mlsolarsystem.models.Weather;
import com.mlsolarsystem.repository.GalaxyRepository;
import com.mlsolarsystem.repository.WeatherRepository;
import com.mlsolarsystem.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by tom
 */
@Service
public class GalaxyService {

    private static final String CREATING_MODEL_FOR_WEATHER_PREDICTION = "Creating model for weather prediction";
    private static final String PREDICTING_WEATHER = "Predicting weather";
    private static final String PREDICTING_WEATHER_AT_RANGE = "Predicting weather in a range of days";
    private final GalaxyRepository galaxyRepository;
    private final WeatherRepository weatherRepository;

    @Autowired
    public GalaxyService(GalaxyRepository galaxyRepository, WeatherRepository weatherRepository) {
        this.galaxyRepository = galaxyRepository;
        this.weatherRepository = weatherRepository;
    }

    public Weather predictWeatherAt(Integer day) {
        return predictWeatherAt(day, new Galaxy<>(galaxyRepository.findAll()));
    }

    private Weather predictWeatherAt(Integer day, Galaxy galaxy) {
        return LoggerUtils.benchmark(PREDICTING_WEATHER, () -> galaxy.predictWeatherAt(day));
    }

    public Prediction predictWeatherAtRange(Integer startDay, Integer endDay) {
        return LoggerUtils.benchmark(PREDICTING_WEATHER_AT_RANGE, () -> {

            List<Weather> weathers = new ArrayList<>();

            weatherRepository.findAllById (
                    IntStream.rangeClosed(startDay, endDay).boxed().map(Object::toString).collect(Collectors.toList())
            ).forEach(weathers::add);

            return new Prediction(
                weathers.stream().filter(Weather::isRain).max(Comparator.comparing(Weather::getIntensity)).orElse(new Weather("-1", BigDecimal.ZERO).sunny()),
                weathers.stream().filter(Weather::isSunny).count(),
                weathers.stream().filter(Weather::isDrought).count(),
                weathers.stream().filter(Weather::isRain).count(),
                weathers.stream().filter(Weather::isOptimumPressureAndTemperature).count()
            );
        });
    }

    @Async
    public void createPlanetModel() {
        createPlanetModel(1, 1000);
    }

    @Async
    public void createPlanetModel(Integer startDay, Integer endDay) {
        LoggerUtils.benchmark(CREATING_MODEL_FOR_WEATHER_PREDICTION, ()-> {
            List<Planet> planets = Arrays.asList(
                new Planet(707.11, "Ferengi", 0),
                new Planet(1000, "Betasoide", 45),
                new Planet(1000, "Vulcano", -45)
            );
            galaxyRepository.saveAll(planets);
            Galaxy galaxy = new Galaxy<>(planets);
            List<Weather> weathers = new ArrayList<>();
            IntStream.rangeClosed(startDay, endDay).forEach(day -> weathers.add(galaxy.predictWeatherAt(day)));
            weatherRepository.saveAll(weathers);
            return null;
        });
    }
}
