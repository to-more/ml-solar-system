package com.mlsolarsystem.services;

import com.mlsolarsystem.models.Weather;
import com.mlsolarsystem.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tom
 */
@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(
            WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getWeatherAt(Integer dia) {
        return weatherRepository.findById(dia.toString()).orElse(new Weather());
    }

    public Weather saveWeatherAt(Weather weather) {
        return weatherRepository.save(weather);
    }

    public List<Weather> getAll() {
        return weatherRepository.findAll();
    }

    public void deleteAll() {
        weatherRepository.deleteAll();
    }

}
