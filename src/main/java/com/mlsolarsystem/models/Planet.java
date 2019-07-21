package com.mlsolarsystem.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Objects;

import static com.mlsolarsystem.utils.MathUtils.scaled;

/**
 * Created by tom
 */
@Document(collection = "galaxy")
public class Planet implements Comparable<Planet> {

    @Field("radius")
    private double radius;
    @Field("name")
    private String name;
    @Field("angular_velocity")
    private BigDecimal angularVelocity;

    public static Planet SUN(){
        return new Planet(0, "SUN", 0);
    }

    public Planet(double radius, String name, double angularVelocity) {
        this.radius = radius;
        this.name = name;
        this.angularVelocity = BigDecimal.valueOf(Math.toRadians(angularVelocity));
    }

    public Position getPositionAt(Time time) {
        double omegaT = angularVelocity.doubleValue() * time.getValue();
        return new Position(
            scaled(radius * Math.cos(omegaT)),
            scaled(radius * Math.sin(omegaT))
        );
    }

    public BigDecimal distanceTo(Planet nextPlanet, Time time) {
        return this.getPositionAt(time)
            .distanceTo(nextPlanet
                .getPositionAt(time));
    }

    @Override
    public int compareTo(Planet planet) {
        if(planet == null){
            return 1;
        }
        return Double.compare(this.radius, planet.radius);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Double.compare(planet.radius, radius) == 0 &&
                Double.compare(planet.angularVelocity.doubleValue(), angularVelocity.doubleValue()) == 0 &&
                Objects.equals(name, planet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, name, angularVelocity);
    }
}
