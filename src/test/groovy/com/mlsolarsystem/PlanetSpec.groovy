package com.mlsolarsystem

import com.mlsolarsystem.models.Planet
import spock.lang.Specification
/**
 * Created by tom
 */
class PlanetSpec extends Specification {


    def "calculates distance to another planet at time"(){
        given:
        Planet planet = new Planet(1, "", 0)
        Planet anotherPlanet = new Planet(1, "", 90)
        when:
        def distance = planet.distanceTo(anotherPlanet)
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
