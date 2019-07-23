package com.mlsolarsystem.models.analyzer;

import com.mlsolarsystem.models.Position;
import com.mlsolarsystem.models.Weather;

import java.util.List;

/**
 * Created by tom
 */
public interface WeatherAnalyzer {
    Weather analyze(List<Position> planets);
}
