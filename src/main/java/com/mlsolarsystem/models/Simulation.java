package com.mlsolarsystem.models;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tomReq on 7/23/19.
 */
public class Simulation implements Analyzable {
    private Integer day;
    private List<Planet> planets;

    public Simulation(Integer day, List<Planet> planets) {
        this.day = day;
        this.planets = planets;
    }

    public Integer getDay() {
        return day;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    @Override
    public List<Position> getPositions() {
        return this.planets.stream().map(Planet::getPosition).collect(Collectors.toList());
    }
}
