package com.mlsolarsystem.models.analyzer;

import com.mlsolarsystem.models.Position;
import com.mlsolarsystem.models.Weather;

import java.util.List;

import static com.mlsolarsystem.models.GalaxyMath.*;

/**
 * Created by tom
 */
public class RainAnalyzer implements WeatherAnalyzer {

    private WeatherAnalyzer next;

    public RainAnalyzer(WeatherAnalyzer next) {
        this.next = next;
    }

    @Override
    public Weather analyze(List<Position> positions) {
        Weather weather = new Weather();
        if(!planetsAreAligned(positions) && containsSun(positions)) {
            weather.rain(calculatePerimeter(positions));
        } else {
            weather = next.analyze(positions);
        }
        return weather;
    }

}
