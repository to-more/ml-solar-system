package com.mlsolarsystem

import com.mlsolarsystem.models.Planet
import com.mlsolarsystem.models.Position
import com.mlsolarsystem.models.Simulation
import com.mlsolarsystem.models.Weather
import com.mlsolarsystem.repository.GalaxyRepository
import com.mlsolarsystem.repository.SimulationRepository
import com.mlsolarsystem.repository.WeatherRepository
import com.mlsolarsystem.services.GalaxyService
import spock.lang.Specification
/**
 * Created by tom
 */
class GalaxyServiceSpec extends Specification {

    GalaxyRepository galaxyRepository  = Mock(GalaxyRepository)
    WeatherRepository weatherRepository = Mock(WeatherRepository)
    SimulationRepository simulationRepository = Mock(SimulationRepository)
    GalaxyService galaxyService = new GalaxyService(galaxyRepository, weatherRepository, simulationRepository)

    def "predict weather at day"(){
        when:
        def weathers = [
            new Weather().sunny(),
            new Weather().rain(),
            new Weather().drought(),
            new Weather().optimumPressureAndTemperature()
        ]
        1 * weatherRepository.findAllById(_ as Iterable<String>) >> weathers
        then:
        def prediction = galaxyService.predictWeatherAtRange(0, 100)
        prediction.daysOfDrought == 1
        prediction.daysOfOptimumPressureAndTemperature == 1
        prediction.daysOfSun == 1
        prediction.daysOfRain == 1
    }

    def "creation of planet model"() {
        when:
        1 * galaxyRepository.saveAll(_ as List<Planet>)
        1000 * simulationRepository.save(_ as Simulation)
        1 * simulationRepository.findAll() >> [
            new Simulation(1, [
                new Planet(20,"p1",0, new Position(10, 10)),
                new Planet(20,"p2",0, new Position(-10, 10)),
                new Planet(20,"p3",0, new Position(0, 10)),
            ])
        ]
        1* weatherRepository.saveAll(_ as List<Weather>)
        then:
        galaxyService.createPlanetModel(1, 1000)
        noExceptionThrown()
    }
}
