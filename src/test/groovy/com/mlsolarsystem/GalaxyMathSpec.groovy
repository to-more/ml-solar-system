package com.mlsolarsystem

import com.mlsolarsystem.models.GalaxyMath
import com.mlsolarsystem.models.Position
import spock.lang.Specification
/**
 * Created by tom
 */
class GalaxyMathSpec extends Specification {

    static boolean checkEquality(double[] numbers) {
        double total = 0
        for (double number : numbers) {
            total += number
        }
        return Math.abs(numbers[0]) == (Math.abs(total) / numbers.length)
    }

    def "check if the planets are aligned"(){
        given: "a galaxy with 3 planets"
        def positions = new ArrayList()
        positions.add ferengi
        positions.add betasoide
        positions.add vulcano

        when: "when check at day 360"
        def aligned = GalaxyMath.planetsAreAligned(positions)
        then: "the planets must to be aligned"
        aligned == expectedResult
        where:
        expectedResult|ferengi                            |betasoide |vulcano
        true | new Position(1, 0)          | new Position(1, 0)  |new Position(1, 0)
        true | new Position(0, 1)          | new Position(0, 2)  |new Position(0, 3)
        true | new Position(-1, 1)         | new Position(0, 1)  | new Position(1, 1)
        true | new Position(1, 1)          | new Position(0, 1)  |new Position(-1, 1)
        true | new Position(0, 1)          | new Position(1, 1)  |new Position(-1, 1)
        true | new Position(1, 0)          | new Position(1, 1)  |new Position(1, -1)
    }

    def "calculate perimeter between planets"(){
        given:
        def positions = new ArrayList()
        positions.add new Position(0, 1)
        positions.add new Position(1, 0)
        positions.add new Position(-1, 0)
        when:
        def perimeterIs = GalaxyMath.calculatePerimeter(positions)
        then:
        perimeterIs as Long == 4
    }

    def "check if the triangle contains the sun"(){
        given:
        def positions = new ArrayList()
        positions.add new Position(1, 1)
        positions.add new Position(0, 2)
        positions.add new Position(-1, -1)
        when:
        def containsSun = GalaxyMath.containsSun(positions)
        then:
        containsSun
    }

    def "check if the planets are aligned with the sun"(){
        given:
        def positions = new ArrayList()
        positions.add new Position(0, 10)
        positions.add new Position(0, 5)
        positions.add new Position(0, 2)
        when:
        def containsSun = GalaxyMath.areAlignedWithTheSun(positions)
        then:
        containsSun
    }
}
