package com.Chahin.GameSnag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;

// TODO: Better checks on REST API Requests
// TODO: Get sale dates
// TODO: new class to handle cleaning out old sales

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class GameSnagApplication {

	static void main(String[] args) {
		SpringApplication.run(GameSnagApplication.class, args);
	}
}
