package com.mlsolarsystem


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
}
