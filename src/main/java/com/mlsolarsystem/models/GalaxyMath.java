package com.mlsolarsystem.models;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mlsolarsystem.models.Planet.SUN;

/**
 * Created by tom
 */
public class GalaxyMath {

    public static Boolean planetsAreAlignedAt(List<Planet> planets, Time time) {

        List<Position> positions = planets.stream().map(planet-> planet.getPositionAt(time))
                .collect(Collectors.toList());

        return areAligned(positions.get(0), positions.get(1), positions.get(2));
    }

    public static Boolean areAlignedWithTheSun(List<Planet> planets, Time time) {
        List<Position> positions = planets.stream().map(planet-> planet.getPositionAt(time))
                .collect(Collectors.toList());
        return planetsAreAlignedAt(planets, time) && areAligned(SUN().getPositionAt(time), positions.get(0), positions.get(1));
    }

    private static boolean areAligned(Position p1, Position p2, Position p3){
        return p1.minus(p2).isProportionalTo(p2.minus(p3));
    }

    public static Boolean containsSunAt(List<Planet> planets, Time time){
        List<Position> positions = planets.stream().map(planet -> planet.getPositionAt(time)).collect(Collectors.toList());
        Triangle triangle = new Triangle(positions.get(0), positions.get(1), positions.get(2));
        return triangle.contains(SUN().getPositionAt(time));
    }

    public static BigDecimal calculatePerimeterAt(List<Planet> planets, Time time){
        BigDecimal perimeter = BigDecimal.ZERO;
        LinkedList<Planet> linkedPlanets = new LinkedList<>(planets);
        for (int i = 0; i < linkedPlanets.size() -1; i++) {
            Planet planet = linkedPlanets.element();
            Planet nextPlanet = planets.get(i + 1);
            perimeter = perimeter.add(planet.distanceTo(nextPlanet, time));
        }
        return perimeter.add(linkedPlanets.getLast().distanceTo(linkedPlanets.getFirst(), time));
    }
}
