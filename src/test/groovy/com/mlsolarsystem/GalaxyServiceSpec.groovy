package com.mlsolarsystem

import com.mlsolarsystem.models.Planet
import com.mlsolarsystem.repository.GalaxyRepository
import com.mlsolarsystem.repository.WeatherRepository
import com.mlsolarsystem.services.GalaxyService
import spock.lang.Specification

/**
 * Created by tom
 */
class GalaxyServiceSpec extends Specification {

    GalaxyRepository galaxyRepository  = Mock(GalaxyRepository)
    WeatherRepository weatherRepository = Mock(WeatherRepository)
    GalaxyService galaxyService = new GalaxyService(galaxyRepository, weatherRepository)

    def "predict weather at day"(){
        when:
        def planets = [
            new Planet(1000, "Ferengi", 10),
            new Planet(200, "Betasoide", 70),
            new Planet(1, "Vulcano", 270)
        ]
        1 * galaxyRepository.findAll() >> planets
        then:
        def weather = galaxyService.predictWeatherAt(100)
        weather.description == "Rain"
    }
}
