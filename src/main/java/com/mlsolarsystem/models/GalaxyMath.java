package com.mlsolarsystem.models;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static com.mlsolarsystem.models.Planet.SUN;

/**
 * Created by tom
 */
public class GalaxyMath {

    public static Boolean planetsAreAligned(List<Position> positions) {
        return areAligned(positions.get(0), positions.get(1), positions.get(2));
    }

    public static Boolean areAlignedWithTheSun(List<Position> positions) {
        return planetsAreAligned(positions) && areAligned(SUN().getPosition(), positions.get(0), positions.get(1));
    }

    private static boolean areAligned(Position p1, Position p2, Position p3){
        return p1.minus(p2).isProportionalTo(p2.minus(p3));
    }

    public static Boolean containsSun(List<Position> positions){
        Triangle triangle = new Triangle(positions.get(0), positions.get(1), positions.get(2));
        return triangle.contains(SUN().getPosition());
    }

    public static BigDecimal calculatePerimeter(List<Position> positions){
        BigDecimal perimeter = BigDecimal.ZERO;
        LinkedList<Position> linkedPlanets = new LinkedList<>(positions);
        for (int i = 0; i < linkedPlanets.size() -1; i++) {
            Position planet = linkedPlanets.element();
            Position nextPlanet = positions.get(i + 1);
            perimeter = perimeter.add(planet.distanceTo(nextPlanet));
        }
        return perimeter.add(linkedPlanets.getLast().distanceTo(linkedPlanets.getFirst()));
    }
}
