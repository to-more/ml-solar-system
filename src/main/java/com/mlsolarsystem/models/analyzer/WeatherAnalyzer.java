package com.mlsolarsystem.models.analyzer;

import com.mlsolarsystem.models.Planet;
import com.mlsolarsystem.models.Time;
import com.mlsolarsystem.models.Weather;

import java.util.List;

/**
 * Created by tom
 */
public interface WeatherAnalyzer {
    Weather analyze(List<Planet> planets, Time time);
}
