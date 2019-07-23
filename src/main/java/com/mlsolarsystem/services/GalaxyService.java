package com.mlsolarsystem.services;

import com.mlsolarsystem.models.*;
import com.mlsolarsystem.repository.GalaxyRepository;
import com.mlsolarsystem.repository.SimulationRepository;
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
    private final SimulationRepository simulationRepository;

    @Autowired
    public GalaxyService(
            GalaxyRepository galaxyRepository,
            WeatherRepository weatherRepository,
            SimulationRepository simulationRepository) {
        this.galaxyRepository = galaxyRepository;
        this.weatherRepository = weatherRepository;
        this.simulationRepository = simulationRepository;
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
                new Planet(500, "Ferengi", 1),
                new Planet(2000, "Betasoide", 3),
                new Planet(1000, "Vulcano", -5)
            );
            galaxyRepository.saveAll(planets);
            Galaxy galaxy = new Galaxy<>(planets);
            List<Weather> weathers = new ArrayList<>();
            List<Simulation> simulations = new ArrayList<>();
            WeatherPredictor weatherPredictor = new WeatherPredictor();

            IntStream.rangeClosed(startDay, endDay).forEach(day -> {
                Time time = new Time(day);
                planets.forEach(planet -> {
                    Movement movement = new Movement(planet.getAngularVelocity().doubleValue(), planet.getRadius());
                    planet.moveTo(movement.move(time));
                });
                simulationRepository.save(new Simulation(day, planets));
            });

            simulationRepository.findAll().forEach(simulation -> weathers.add(weatherPredictor.predictWeather(simulation)));
            weatherRepository.saveAll(weathers);
            simulationRepository.saveAll(simulations);
            return null;
        });
    }
}
