package com.Chahin.GameSnag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;

// TODO: Better checks on REST API Requests + Scraping
// TODO: Save EpicGamesScraper games to MongoDB 1x then begin work on front end for further testing
// TODO: Clean up EpicGamesScraper. Figure out LocalPath null issues
// TODO: Add images to project root and make custom resourcehandler so runtime updates are shown live


@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class GameSnagApplication {

	static void main(String[] args) {
		SpringApplication.run(GameSnagApplication.class, args);
	}
}
