package com.Chahin.GameSnag.Repository;

import com.Chahin.GameSnag.Entities.Game;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSnagRepository extends MongoRepository<Game, ObjectId> {
    Game findByTitle(String title);
    void deleteByTitle(String title);
}
