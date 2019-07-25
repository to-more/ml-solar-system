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

    public static Boolean planetsAreAligned(List<Position> positions) {
        List<Position> sortedPositions = positions.stream().sorted().collect(Collectors.toList());
        return areAligned(sortedPositions.get(0), sortedPositions.get(1), sortedPositions.get(2));
    }

    public static Boolean areAlignedWithTheSun(List<Position> positions) {
        return planetsAreAligned(positions) && areAligned(SUN().getPosition(), positions.get(0), positions.get(1));
    }

    private static boolean coordinatesAreEquals(double[] coords) {
        double sum = 0;
        for (double number : coords) {
            sum += number;
        }
        return (Math.abs(coords[0]) == (Math.abs(sum)/coords.length));
    }

    private static boolean areAligned(Position p1, Position p2, Position p3){
        if(coordinatesAreEquals(new double[]{p1.getX(), p2.getX(), p3.getX()}) ||
            coordinatesAreEquals(new double[]{p1.getY(), p2.getY(), p3.getY()}))
                return true;

        return (p1.distanceTo(p2).doubleValue() + p2.distanceTo(p3).doubleValue()) == p1.distanceTo(p3).doubleValue();
//        return p2.minus(p1).isProportionalTo(p3.minus(p2));
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
