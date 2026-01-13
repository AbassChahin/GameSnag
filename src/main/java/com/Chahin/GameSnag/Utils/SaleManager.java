package com.Chahin.GameSnag.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Clean out old sales + local photos
@Configuration
public class SaleManager {

    @Autowired
    private final MongoTemplate mongoTemplate;

    public SaleManager(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void deleteAllFiles() {
        Path dir = Paths.get("./images/games");
        try (Stream<Path> paths = Files.list(dir)) {
            paths.forEach(
                    path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            System.out.println("[FILE DELETION] - Failed - Unable to delete file: " + path);
                            e.printStackTrace();
                        }
                    }
            );
            System.out.println("[FILE DELETION] - Successful - All files deleted!");
        } catch (IOException e) {
            System.out.println("[FILE DELETION] - Failed - Unable to locate files in: " + dir);
            e.printStackTrace();
        }
    }

    public void deleteAllDocuments() {
        mongoTemplate.remove(new Query(), "GameSnag");
        System.out.println("[DOCUMENT DELETION] - Successful - All documents deleted!");
    }
}
