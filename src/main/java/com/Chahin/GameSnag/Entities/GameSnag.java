package com.Chahin.GameSnag.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "GameSnag")
public class GameSnag {

    @Id
    @Field("Name")
    private String name;

    @Field("OriginalPrice")
    private double originalPrice;

    @Field("SalePrice")
    private double salePrice;

    @Field("SaleDates")
    private String saleDates;

    public GameSnag() {
    }

    public GameSnag(String name, float originalPrice, float salePrice, String saleDates) {
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

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
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
        return "Game: " + name + "\nOriginal Price: " + originalPrice + "\nSale Price: " + salePrice + "\nSale Dates: " + saleDates;
    }
}
