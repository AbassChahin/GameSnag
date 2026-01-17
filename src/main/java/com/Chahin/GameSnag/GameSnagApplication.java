package com.Chahin.GameSnag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: Set up process on Oracle VM to run in the background Nginx for front end and Spring program in the backend
// TODO: Split scheduler / scraping into separate program

@SpringBootApplication(
		excludeName = {
			"org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration",
			"org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration"
		}
)
@EnableMongoRepositories(basePackages = "com.Chahin.GameSnag.Repository")
@EnableScheduling
public class GameSnagApplication {

	static void main(String[] args) {
		SpringApplication.run(GameSnagApplication.class, args);
	}
}
