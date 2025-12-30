package com.Chahin.GameSnag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.mongodb.autoconfigure.MongoAutoConfiguration;

// TODO: Figure out why Get Requests on the game name keep returning null

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class GameSnagApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameSnagApplication.class, args);
	}
}
