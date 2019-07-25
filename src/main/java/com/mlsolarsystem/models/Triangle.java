package com.mlsolarsystem.models;

import org.apache.commons.math3.analysis.function.Abs;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by tom
 */
public class Triangle {

    private Position a;
    private Position b;
    private Position c;

    public Triangle(Position a, Position b, Position c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean areCollinear(){
        Vector2D vectorA = new Vector2D(a.asCoordinates());
        Vector2D vectorB = new Vector2D(b.asCoordinates());
        Vector2D vectorC = new Vector2D(c.asCoordinates());

        return vectorA.distanceInf(vectorC) ==
        vectorA.distanceInf(vectorB) +
        vectorB.distanceInf(vectorC);
    }

    public boolean contains(Position queryPoint) {
        Vector2D queryVector    = new Vector2D(queryPoint.asCoordinates());
        Vector2D vectorA        = new Vector2D(a.asCoordinates());
        Vector2D vectorB        = new Vector2D(b.asCoordinates());
        Vector2D vectorC        = new Vector2D(c.asCoordinates());
        Abs abs = new Abs();
        return (
            abs.value(queryVector.crossProduct(vectorA, vectorB)) +
            abs.value(queryVector.crossProduct(vectorA, vectorC)) +
            abs.value(queryVector.crossProduct(vectorB, vectorC))
        ) <= abs.value(vectorA.crossProduct(vectorB, vectorC));
    }
}
