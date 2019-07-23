package com.mlsolarsystem.models;

import static com.mlsolarsystem.utils.MathUtils.scaled;

/**
 * Created by tom
 */
public class Movement {


    private double angularVelocity;
    private double radius;


    public Movement(double angularVelocity, double radius) {
        this.angularVelocity = angularVelocity;
        this.radius = radius;
    }

    public Position move(Time time){
        double omegaT = angularVelocity * time.getValue();
        return new Position(
            scaled(radius * Math.cos(omegaT)),
            scaled(radius * Math.sin(omegaT))
        );
    }
}
