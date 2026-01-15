package com.Chahin.GameSnag.Repository;

import com.Chahin.GameSnag.Entities.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, ObjectId> {
    Message findByName(String name);
    Message findByEmail(String email);
    void deleteByName(String name);
    void deleteByEmail(String name);
}
