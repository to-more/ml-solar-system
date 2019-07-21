package com.mlsolarsystem;

import com.mlsolarsystem.services.GalaxyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SolarSystemApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SolarSystemApplication.class, args);
		context.getBean(GalaxyService.class).createPlanetModel();
	}
}
