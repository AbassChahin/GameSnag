package com.Chahin.GameSnag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: Make Scrape happen @ 12AM daily

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableScheduling
public class GameSnagApplication {

	static void main(String[] args) {
		SpringApplication.run(GameSnagApplication.class, args);
	}
}
