package com.mlsolarsystem.models;

import static java.lang.Math.round;

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

    public Position move(Position current, Time time){
        double omegaT = Math.toRadians(angularVelocity * time.getValue());
        return new Position(
            round((current.getX() * Math.cos(omegaT)) - (current.getY() * Math.sin(omegaT))),
            round((current.getY() * Math.cos(omegaT)) + (current.getX() * Math.sin(omegaT)))
        );
    }
}
