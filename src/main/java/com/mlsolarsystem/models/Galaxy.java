package com.mlsolarsystem.models;

import com.mlsolarsystem.models.analyzer.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tom
 */
public class Galaxy<T extends Planet> {
    private List<Planet> planets;

    public Galaxy() {
        planets = new LinkedList<>();
    }
    public Galaxy(List<T> planets) {
        this.planets = planets.stream().sorted(Planet::compareTo).collect(Collectors.toCollection(LinkedList::new));
    }

    public void add(T planet){
        planets.add(planet);
        this.planets = planets.stream().sorted(Planet::compareTo).collect(Collectors.toCollection(LinkedList::new));
    }
}
