package com.mlsolarsystem.models;

import com.mlsolarsystem.models.analyzer.*;

/**
 * Created by tom
 */
public class WeatherPredictor {
    private WeatherAnalyzer weatherAnalyzer;

    public WeatherPredictor(){
        this.weatherAnalyzer = new DroughtAnalyzer(new OptimumAnalyzer(new RainAnalyzer(new SunnyAnalyzer(null))));
    }


    public Weather predictWeather(Analyzable analyzable){
        return weatherAnalyzer.analyze(analyzable.getPositions());
    }

}
