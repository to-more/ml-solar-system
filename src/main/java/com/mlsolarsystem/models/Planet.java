package com.mlsolarsystem.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by tom
 */
@Document(collection = "galaxy")
public class Planet implements Movable, Comparable<Planet> {

    @Id
    private String name;
    private double radius;
    @Field("angular_velocity")
    private BigDecimal angularVelocity;
    private Position position;

    public static Planet SUN(){
        return new Planet(0, "SUN", 0, new Position(0, 0));
    }

    public Planet(){
        super();
        this.position = new Position(0, 0);
    }

    public Planet(double radius, String name, double angularVelocity) {
        this.radius = radius;
        this.name = name;
        this.angularVelocity = BigDecimal.valueOf(angularVelocity);
        this.position = new Position(0, 0);
    }

    public Planet(double radius, String name, double angularVelocity, Position position) {
        this.radius = radius;
        this.name = name;
        this.angularVelocity = BigDecimal.valueOf(angularVelocity);
        this.position = position;
    }


    public String getName() {
        return name;
    }

    public double getRadius() {
        return radius;
    }

    public BigDecimal getAngularVelocity() {
        return angularVelocity;
    }

    public BigDecimal distanceTo(Planet nextPlanet) {
        return this.getPosition()
            .distanceTo(nextPlanet
                .getPosition());
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

    public Position getPosition() {
        return position;
    }

    @Override
    public void moveTo(Position position) {
        this.position.plus(position);
    }
}
