package com.Chahin.GameSnag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: Make Scrape happen @ 12AM daily

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
