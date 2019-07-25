package com.mlsolarsystem.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by tom
 */
@Document(collection = "galaxy")
public class Position {

    private double x;
    private double y;

    public Position(){
        super();
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isProportionalTo(Position positionAt) {
        return this.equals(positionAt)
            || coordinatesAreEquals(x, positionAt.x)
            || coordinatesAreEquals(y, positionAt.y)
            || Math.abs(y/positionAt.y) == Math.abs(x/positionAt.x);
    }

    private boolean coordinatesAreEquals(double c1, double c2) {
        return Double.compare(c1, c2) == 0;
    }

    public BigDecimal distanceTo(Position position) {
        return BigDecimal.valueOf(Math.sqrt(Math.pow(position.getY() - getY(), 2) + Math.pow(position.getX() - getX(), 2)));
    }

    public Position minus(Position positionAt) {
        return new Position(this.x - positionAt.x, this.y - positionAt.y);
    }

    public double[] asCoordinates() {
        return new double[]{ x, y };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return coordinatesAreEquals(x, position.x) && coordinatesAreEquals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Position.class.getSimpleName() + "[", "]")
                .add("x=" + x)
                .add("y=" + y)
                .toString();
    }

    public void plus(Position position) {
        this.x += position.x;
        this.y += position.y;
    }
}
