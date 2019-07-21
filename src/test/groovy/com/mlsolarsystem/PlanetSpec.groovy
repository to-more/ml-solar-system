package com.mlsolarsystem

import com.mlsolarsystem.models.Planet
import com.mlsolarsystem.models.Position
import com.mlsolarsystem.models.Time
import spock.lang.Specification
/**
 * Created by tom
 */
class PlanetSpec extends Specification {

    def "get the actual position at time"(){
        given:
        Planet planet = new Planet(1, "", 90)
        when:
        def position = planet.getPositionAt(new Time(2))
        def anotherPosition = new Position(-1,0)
        then:
        position.getX() == anotherPosition.getX()
        position.getY() == anotherPosition.getY()
    }

    def "calculates distance to another planet at time"(){
        given:
        Planet planet = new Planet(1, "", 0)
        Planet anotherPlanet = new Planet(1, "", 90)
        when:
        def distance = planet.distanceTo(anotherPlanet, new Time(4))
        then:
        distance == 0
    }

    def "check comparation"(planet1, planet2, expected){
        expect:
            planet1.compareTo(planet2) == expected
        where:
            planet1|planet2|expected
            new Planet(0,"",0)  | new Planet(2,"",0) |-1
            new Planet(0,"",0)  | null               | 1
            new Planet(20,"",0) | new Planet(2,"",0) | 1
            new Planet(2,"",0)  | new Planet(2,"",0) | 0
    }

    def "check equals"(planet1, planet2, expectedResult){
        expect: (planet1.equals(planet2)) == expectedResult
        where:
        planet1|planet2|expectedResult
        new Planet(0,"",0)|new Planet(0,"",0)|true
        new Planet(0,"",0)|new Planet(1,"",0)|false
    }
}
