package com.mlsolarsystem

import com.mlsolarsystem.models.Position
import spock.lang.Specification

/**
 * Created by tom
 */
class PositionSpec extends Specification {

    def "check if two position are equivalents"(){
        given:
        Position position = new Position(1, 0)
        Position anotherPosition = new Position(10, 0)
        when:
        def isEquivalent = position.isProportionalTo(anotherPosition)
        then:
        isEquivalent
    }

    def "calculates the distance between positions"(){
        given:
        Position position = new Position(1, 0)
        Position anotherPosition = new Position(10, 0)
        when:
        def distanceBetween = position.distanceTo(anotherPosition)
        then:
        distanceBetween == 9
    }
}
