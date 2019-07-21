package com.mlsolarsystem

import com.fasterxml.jackson.databind.ObjectMapper
import com.mlsolarsystem.controllers.WeatherController
import com.mlsolarsystem.models.Weather
import com.mlsolarsystem.repository.WeatherRepository
import com.mlsolarsystem.services.WeatherService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
/**
 * Created by tom
 */
class WeathersControllerSpec extends Specification {


    def weatherRepository = Mock(WeatherRepository)
    def weatherService = new WeatherService(weatherRepository)
    def weathersController = new WeatherController(weatherService)

    MockMvc mockMvc = standaloneSetup(weathersController).build()

    def "perform get by day"(){
        when:
        1 * weatherRepository.findById("1") >> Optional.of(new Weather())
        def response = mockMvc
            .perform(get("/weathers/1").contentType(MediaType.APPLICATION_JSON))
            .andReturn().response
        then:
        response.status == HttpStatus.OK.value()
    }

    def "perform get all"(){
        when:
        def response = mockMvc
            .perform(get("/weathers").contentType(MediaType.APPLICATION_JSON))
            .andReturn().response
        then:
        response.status == HttpStatus.OK.value()
    }

    def "perform post"(){
        when:
        def response = mockMvc
            .perform(post("/weathers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper.newInstance().writeValueAsString(new Weather("1", BigDecimal.ZERO)))
        ).andReturn().response
        then:
        response.status == HttpStatus.CREATED.value()
    }

    def "perform delete all"(){
        when:
        def response = mockMvc
            .perform(delete("/weathers").contentType(MediaType.APPLICATION_JSON))
            .andReturn().response
        then:
        response.status == HttpStatus.OK.value()
    }
}
