package com.Chahin.GameSnag.Entities;
import com.Chahin.GameSnag.Utils.Platform;
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

    @Field("ImagePath")
    private String imagePath;

    @Field("Platform")
    private Platform platform;

    @Field("ReferenceURL")
    private String referenceURL;

    public Game() {
    }

    public Game(String title, double originalPrice, double salePrice, String imagePath, Platform platform, String referenceURL) {
        this.title = title;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.imagePath = imagePath;
        this.platform = platform;
        this.referenceURL = referenceURL;
    }

    public Game(ObjectId _id, String title, double originalPrice, double salePrice, String imagePath, Platform platform, String referenceURL) {
        this._id = _id;
        this.title = title;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.imagePath = imagePath;
        this.platform = platform;
        this.referenceURL = referenceURL;
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

    public String getImagePath() {return imagePath;}

    public void setImagePath(String imagePath) {this.imagePath = imagePath;}

    public Platform getPlatform() {return platform;}

    public void setPlatform(Platform platform) {this.platform = platform;}

    public String getReferenceURL() {return referenceURL;}

    public void setReferenceURL(String referenceURL) {this.referenceURL = referenceURL;}

    @Override
    public String toString() {
        return "Title: " + title + "\nOriginal Price: " + originalPrice + "\nSale Price: " + salePrice + "\nPlatform: " + platform + "\nImage Path: " + imagePath  + "\nReference URL: " + referenceURL;
    }
}
