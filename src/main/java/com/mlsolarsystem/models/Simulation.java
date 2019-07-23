package com.mlsolarsystem.models;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tomReq on 7/23/19.
 */
public class Simulation implements Analyzable {
    @Id
    private String day;
    private List<Planet> planets;

    public Simulation(){ super(); }

    public Simulation(Integer day, List<Planet> planets) {
        this.day = day.toString();
        this.planets = planets;
    }

    public String getDay() {
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
