package com.msys.water_station;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class WaterStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterStationApplication.class, args);
	}

}
