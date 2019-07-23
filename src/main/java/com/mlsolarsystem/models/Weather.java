package com.mlsolarsystem.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Created by tom
 */
@Document(collection = "weather")
public class Weather {

    private static final String RAIN = "Rain";
    private static final String OPTIMUM_PRESSURE_AND_TEMPERATURE = "Optimum pressure and temperature";
    private static final String DROUGHT = "Drought";
    private static final String SUNNY = "Sunny";

    @Id
    private String day;
    private String description;
    private BigDecimal intensity;

    public Weather() {
        intensity = BigDecimal.ZERO;
    }

    public Weather(String day, BigDecimal intensity) {
        this.day = day;
        this.intensity = intensity;
    }

    public String getDay() { return day; }
    public String getDescription() {
        return description;
    }
    public BigDecimal getIntensity() {
        return intensity;
    }
    public Weather withDay(Integer day) {
        this.day = day.toString();
        return this;
    }

    public Weather rain() {
        description = RAIN;
        return this;
    }

    public Weather rain(BigDecimal intensity) {
        description = RAIN;
        this.intensity = intensity;
        return this;
    }

    public Weather optimumPressureAndTemperature() {
        description = OPTIMUM_PRESSURE_AND_TEMPERATURE;
        return this;
    }

    public Weather drought() {
        description = DROUGHT;
        return this;
    }

    public Weather sunny() {
        description = SUNNY;
        return this;
    }

    public boolean isOptimumPressureAndTemperature() {
        return OPTIMUM_PRESSURE_AND_TEMPERATURE.equals(this.description);
    }

    public boolean isRain() {
        return RAIN.equals(this.description);
    }

    public boolean isDrought() {
        return DROUGHT.equals(this.description);
    }

    public boolean isSunny() {
        return SUNNY.equals(this.description);
    }
}
