package com.mlsolarsystem.models.analyzer;

import com.mlsolarsystem.models.Planet;
import com.mlsolarsystem.models.Time;
import com.mlsolarsystem.models.Weather;

import java.math.BigDecimal;
import java.util.List;

import static com.mlsolarsystem.models.GalaxyMath.containsSunAt;
import static com.mlsolarsystem.models.GalaxyMath.planetsAreAlignedAt;

/**
 * Created by tom
 */
public class SunnyAnalyzer implements WeatherAnalyzer {

    private WeatherAnalyzer next;

    public SunnyAnalyzer(WeatherAnalyzer next) {
        this.next = next;
    }

    @Override
    public Weather analyze(List<Planet> planets, Time time) {
        Weather weather = new Weather(time.getValue().toString(), BigDecimal.ZERO);
        if(!planetsAreAlignedAt(planets, time) && !containsSunAt(planets, time)) {
            weather.sunny();
        }
        return weather;
    }
}
