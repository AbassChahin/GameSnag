package com.Chahin.GameSnag.Config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        String uri = System.getenv("MONGO_URI");
        return new MongoTemplate(MongoClients.create(uri), System.getenv("MONGO_DB_NAME"));
    }
}
