package com.mlsolarsystem

import com.mlsolarsystem.models.Movement
import com.mlsolarsystem.models.Position
import com.mlsolarsystem.models.Time
import spock.lang.Specification
/**
 * Created by tom
 */
class MovementSpec extends Specification {

    void "doing an angular movement"(){
        when:
        Movement movement = new Movement(degrees, 1)
        def position = movement.move(new Position(0, 1), new Time(1))
        then:
        position == expectedPosition
        where:
        expectedPosition        | degrees
        new Position(0.0, 1.0)  | 0
        new Position(-1.0, 0.0) | 90
        new Position(0.0, -1.0) | 180
        new Position(1.0, 0.0)  | 270
        new Position(0.0, 1.0)  | 360
    }
}
