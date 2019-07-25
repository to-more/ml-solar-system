package com.mlsolarsystem

import com.mlsolarsystem.models.Planet
import com.mlsolarsystem.models.Position
import com.mlsolarsystem.models.Simulation
import com.mlsolarsystem.models.WeatherPredictor
import spock.lang.Specification
/**
 * Created by tom
 */
class GalaxySpec extends Specification {

    def "predict weather at specific day"(){
        given:
        WeatherPredictor weatherPredictor = new WeatherPredictor()
        def planets = new ArrayList()
        def ferengi = new Planet()
        def betasoide = new Planet()
        def vulcano = new Planet()
        ferengi.moveTo(ferengiPos as Position)
        betasoide.moveTo(betasoidePos as Position)
        vulcano.moveTo(vulcanoPos as Position)

        planets.add ferengi
        planets.add betasoide
        planets.add vulcano

        def simulation = new Simulation(day, planets)

        when:
        def weather = weatherPredictor.predictWeather(simulation)
        then:
        weather.description == expectedWeather
        where:
        expectedWeather | day | ferengiPos | betasoidePos | vulcanoPos
        "Drought"| 1 |new Position(0, 10) |new Position(0, 20)|new Position(0, 30)
        "Rain"|1|new Position(1, 1)|new Position(-1, 1)|new Position(0, -1)
        "Sunny"|1|new Position(1000, 1000)|new Position(-200, 200)|new Position(0, 1)
        "Optimum pressure and temperature"|1|new Position(-1, -1)|new Position(0, -1)|new Position(1, -1)
        "Optimum pressure and temperature"|1|new Position(1, 0)|new Position(1, -1)|new Position(1, 2)
    }
}
