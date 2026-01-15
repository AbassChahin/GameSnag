package com.Chahin.GameSnag.Entities;

import com.Chahin.GameSnag.Utils.Platform;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@TypeAlias("Message")
@Document(collection = "Messages")
public class Message {
    @Id
    @Field("_id")
    private ObjectId _id;

    @Field("Name")
    private String name;

    @Field("Email")
    private String email;

    @Field("Message")
    private String message;

    public Message() {
    }

    public Message(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public Message(ObjectId _id, String name, String email, String message) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nEmail: " + email + "\nMessage: " + message;
    }
}
