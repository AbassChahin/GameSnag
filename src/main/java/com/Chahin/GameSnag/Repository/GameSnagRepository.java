package com.Chahin.GameSnag.Repository;

import com.Chahin.GameSnag.Entities.Game;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameSnagRepository extends MongoRepository<Game, ObjectId> {

    Game findByName(String name);
    Game deleteByName(String name);
    List<Game> findBySalePrice(double salePrice);
}
