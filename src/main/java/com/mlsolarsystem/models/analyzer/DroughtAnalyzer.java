package com.mlsolarsystem.models.analyzer;

import com.mlsolarsystem.models.Planet;
import com.mlsolarsystem.models.Position;
import com.mlsolarsystem.models.Weather;

import java.util.List;

import static com.mlsolarsystem.models.GalaxyMath.areAlignedWithTheSun;
import static com.mlsolarsystem.models.GalaxyMath.planetsAreAligned;

/**
 * Created by tom
 */
public class DroughtAnalyzer implements WeatherAnalyzer {

    private WeatherAnalyzer next;

    public DroughtAnalyzer(WeatherAnalyzer next){
        this.next = next;
    }

    @Override
    public Weather analyze(List<Position> planets) {

        Weather weather = new Weather();

        if(planetsAreAligned(planets) && areAlignedWithTheSun(planets)){
            weather.drought();
        } else {
            weather = next.analyze(planets);
        }

        return weather;
    }
}
