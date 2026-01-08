package com.Chahin.GameSnag;

import com.Chahin.GameSnag.Utils.SteamGamesScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;

import java.io.IOException;

// TODO: Better checks on REST API Requests + Scraping
// TODO: Clean up EpicGamesScraper. Figure out LocalPath null issues
// TODO: Clean up SteamGamesScraper

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class GameSnagApplication {

	static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(GameSnagApplication.class, args);
	}
}
