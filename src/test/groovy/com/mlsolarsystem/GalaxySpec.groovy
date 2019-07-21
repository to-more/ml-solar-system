package com.mlsolarsystem

import com.mlsolarsystem.models.Galaxy
import com.mlsolarsystem.models.Planet
import spock.lang.Specification
/**
 * Created by tom
 */
class GalaxySpec extends Specification {

    def "predict weather at specific day"(){
        given:
        Galaxy galaxy = new Galaxy()
        galaxy.add ferengi
        galaxy.add betasoide
        galaxy.add vulcano
        when:
        def weather = galaxy.predictWeatherAt(day)
        then:
        weather.description == expectedWeather
        where:
        expectedWeather | day | ferengi | betasoide | vulcano
        "Drought"|1|new Planet(1000, "Ferengi", 90)|new Planet(200, "Betasoide", 90)|new Planet(1, "Vulcano", 90)
        "Rain"|1|new Planet(1, "Ferengi", 45)|new Planet(1, "Betasoide", 135)|new Planet(1, "Vulcano", 270)
        "Sunny"|1|new Planet(1000, "Ferengi", 45)|new Planet(200, "Betasoide", 135)|new Planet(1, "Vulcano", 90)
        "Optimum pressure and temperature"|1|new Planet(1, "Ferengi", 225)|new Planet(1, "Betasoide", 270)|new Planet(1, "Vulcano", 315)
    }
}
