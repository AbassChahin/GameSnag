package com.Chahin.GameSnag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;

// TODO: Better checks on REST API Requests in service class
// TODO: Front End - Make search bar work around button filters
// TODO: Make Contact Us page save messages to DB

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class GameSnagApplication {

	static void main(String[] args) {
		SpringApplication.run(GameSnagApplication.class, args);
	}
}
