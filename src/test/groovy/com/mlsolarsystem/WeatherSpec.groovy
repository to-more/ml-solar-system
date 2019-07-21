package com.mlsolarsystem

import com.mlsolarsystem.models.Weather
import spock.lang.Specification

/**
 * Created by tom
 */
class WeatherSpec extends Specification {

    def "check rain description"(){
        expect:
        new Weather().rain().description == "Rain"
    }
    def "check Drought description"(){
        expect:
        new Weather().drought().description == "Drought"
    }
    def "check Optimum pressure and temperature description"(){
        expect:
        new Weather().optimumPressureAndTemperature().description == "Optimum pressure and temperature"
    }
}
