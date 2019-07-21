package com.mlsolarsystem


import com.mlsolarsystem.models.GalaxyMath
import com.mlsolarsystem.models.Planet
import com.mlsolarsystem.models.Time
import spock.lang.Specification
/**
 * Created by tom
 */
class GalaxyMathSpec extends Specification {

    def "check if the planets are aligned"(){
        given: "a galaxy with 3 planets"
        def planets = new ArrayList()
        planets.add ferengi
        planets.add betasoide
        planets.add vulcano

        when: "when check at day 360"
        def aligned = GalaxyMath.planetsAreAlignedAt(planets, new Time(day))
        then: "the planets must to be aligned"
        aligned == expectedResult
        where:
        expectedResult|day|ferengi                 |betasoide                         |vulcano
        true | 360|new Planet(1, "Ferengi", 1)     | new Planet(1, "Betasoide", 360)  |new Planet(1, "Vulcano", -360)
        true | 1  |new Planet(1000, "Ferengi", 90) | new Planet(200, "Betasoide", 90) |new Planet(1, "Vulcano", 90)
    }

    def "calculate perimeter between planets"(){
        given:
        def planets = new ArrayList()
        planets.add new Planet(1, "Ferengi", 90)
        planets.add new Planet(1, "Betasoide", 360)
        planets.add new Planet(1, "Vulcano", -360)
        when:
        def perimeterIs = GalaxyMath.calculatePerimeterAt(planets, new Time(1))
        then:
        perimeterIs as Long == 4
    }

    def "check if the triangle contains the sun"(){
        given:
        def planets = new ArrayList()
        planets.add new Planet(1, "Ferengi", 45)
        planets.add new Planet(2, "Betasoide", 270)
        planets.add new Planet(1, "Vulcano", 135)
        when:
        def containsSun = GalaxyMath.containsSunAt(planets, new Time(1))
        then:
        containsSun
    }

    def "check if the planets are aligned with the sun"(){
        given:
        def planets = new ArrayList()
        planets.add new Planet(1000, "Ferengi", 90)
        planets.add new Planet(200, "Betasoide", 90)
        planets.add new Planet(1, "Vulcano", 90)
        when:
        def containsSun = GalaxyMath.areAlignedWithTheSun(planets, new Time(1))
        then:
        containsSun
    }
}
