package com.Chahin.GameSnag.Repository;

import com.Chahin.GameSnag.Entities.GameSnag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameSnagRepository extends MongoRepository<GameSnag, String> {

    GameSnag findByName(String name);
    GameSnag deleteByName(String name);
    List<GameSnag> findBySalePrice(double salePrice);
}
