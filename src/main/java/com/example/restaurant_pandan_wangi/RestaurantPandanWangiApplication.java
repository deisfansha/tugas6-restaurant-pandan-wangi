package com.example.restaurant_pandan_wangi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantPandanWangiApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "{name propertires}");
		SpringApplication.run(RestaurantPandanWangiApplication.class, args);
	}

}
