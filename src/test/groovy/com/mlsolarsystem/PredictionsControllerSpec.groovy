package com.mlsolarsystem

import com.mlsolarsystem.controllers.PredictionController
import com.mlsolarsystem.models.Weather
import com.mlsolarsystem.repository.GalaxyRepository
import com.mlsolarsystem.repository.WeatherRepository
import com.mlsolarsystem.services.GalaxyService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
/**
 * Created by tom
 */
class PredictionsControllerSpec extends Specification {

    def galaxyRepository = Mock(GalaxyRepository)
    def weatherRepository = Mock(WeatherRepository)
    def galaxyService = new GalaxyService(galaxyRepository, weatherRepository)
    def predictionController = new PredictionController(galaxyService)

    MockMvc mockMvc = standaloneSetup(predictionController).build()

    def "perform a get of predictions"(){
        when:

        1 * weatherRepository.findAllById(_ as Iterable<String>) >> [
            new Weather("1", BigDecimal.TEN).rain(),
            new Weather("2", BigDecimal.TEN).rain(),
            new Weather("3", BigDecimal.ZERO).drought(),
            new Weather("4", BigDecimal.ZERO).sunny(),
            new Weather("5", BigDecimal.ZERO).drought(),
            new Weather("6", BigDecimal.ZERO).sunny(),
            new Weather("7", BigDecimal.ZERO).optimumPressureAndTemperature(),
            new Weather("8", BigDecimal.ZERO).optimumPressureAndTemperature()
        ]

        def response = mockMvc
            .perform(get("/predictions?startDay=1&endDay=1000")
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn().response
        then:
        response.status == HttpStatus.OK.value()
    }
}
