package com.Chahin.GameSnag.Entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@TypeAlias("Game")
@Document(collection = "GameSnag")
public class Game {

    @Id
    @Field("_id")
    private ObjectId _id;

    @Field("Name")
    private String name;

    @Field("OriginalPrice")
    private double originalPrice;

    @Field("SalePrice")
    private double salePrice;

    @Field("SaleDates")
    private String saleDates;

    public Game() {
    }

    public Game(String name, float originalPrice, float salePrice, String saleDates) {
        this.name = name;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.saleDates = saleDates;
    }

    public Game(ObjectId _id, String name, double originalPrice, double salePrice, String saleDates) {
        this._id = _id;
        this.name = name;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.saleDates = saleDates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getSaleDates() {
        return saleDates;
    }

    public void setSaleDates(String saleDates) {
        this.saleDates = saleDates;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nOriginal Price: " + originalPrice + "\nSale Price: " + salePrice + "\nSale Dates: " + saleDates;
    }
}
