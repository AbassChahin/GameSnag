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

    @Field("Title")
    private String title;

    @Field("OriginalPrice")
    private double originalPrice;

    @Field("SalePrice")
    private double salePrice;

    @Field("SaleDates")
    private String saleDates;

    @Field("ImagePath")
    private String imagePath;

    public Game() {
    }

    public Game(String title, double originalPrice, double salePrice, String saleDates, String imagePath) {
        this.title = title;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.saleDates = saleDates;
        this.imagePath = imagePath;
    }

    public Game(ObjectId _id, String title, double originalPrice, double salePrice, String saleDates, String imagePath) {
        this._id = _id;
        this.title = title;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.saleDates = saleDates;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
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

    public String getImagePath() {return imagePath;}

    public void setImagePath(String imagePath) {this.imagePath = imagePath;}

    @Override
    public String toString() {
        return "Title: " + title + "\nOriginal Price: " + originalPrice + "\nSale Price: " + salePrice + "\nSale Dates: " + saleDates + "\nImage Path: " + imagePath;
    }
}
