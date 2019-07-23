package com.mlsolarsystem.models.analyzer;

import com.mlsolarsystem.models.Planet;
import com.mlsolarsystem.models.Position;
import com.mlsolarsystem.models.Weather;

import java.util.List;

import static com.mlsolarsystem.models.GalaxyMath.calculatePerimeter;
import static com.mlsolarsystem.models.GalaxyMath.containsSun;
import static com.mlsolarsystem.models.GalaxyMath.planetsAreAligned;

/**
 * Created by tom
 */
public class RainAnalyzer implements WeatherAnalyzer {

    private WeatherAnalyzer next;

    public RainAnalyzer(WeatherAnalyzer next) {
        this.next = next;
    }

    @Override
    public Weather analyze(List<Position> planets) {
        Weather weather = new Weather();
        if(!planetsAreAligned(planets) && containsSun(planets)) {
            weather.rain(calculatePerimeter(planets));
        } else {
            weather = next.analyze(planets);
        }
        return weather;
    }

}
