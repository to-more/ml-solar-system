package com.mlsolarsystem.models;

/**
 * Created by tom
 */
public class Prediction {
    private long daysOfDrought;
    private long daysOfRain;
    private long daysOfOptimumPressureAndTemperature;
    private long daysOfSun;
    private Weather mostRainyDay;

    public Prediction(Weather mostRainyDay, long daysOfSun, long daysOfDrought, long daysOfRain, long daysOfOptimumPressureAndTemperature) {
        this.mostRainyDay = mostRainyDay;
        this.daysOfSun = daysOfSun;
        this.daysOfDrought = daysOfDrought;
        this.daysOfRain = daysOfRain;
        this.daysOfOptimumPressureAndTemperature = daysOfOptimumPressureAndTemperature;
    }

    public long getDaysOfSun() {
        return daysOfSun;
    }

    public Weather getMostRainyDay() {
        return mostRainyDay;
    }

    public long getDaysOfDrought() {
        return daysOfDrought;
    }

    public long getDaysOfRain() {
        return daysOfRain;
    }

    public long getDaysOfOptimumPressureAndTemperature() {
        return daysOfOptimumPressureAndTemperature;
    }
}
