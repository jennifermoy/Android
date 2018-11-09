package edu.chapman.jennifer.restaurantapp;

import android.os.Parcelable;

import java.io.Serializable;

public class Restaurant implements Serializable{

    String name;
    String phone;
    String website;
    float rating;
    String category;

    public Restaurant(String name, String phone, String website, float rating, String category) {
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public float getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }


    @Override
    public String toString() {
        return (this.getName() + "  " + this.getPhone() + "  " + this.getWebsite() + "  " + this. getCategory()).toString();
    }
}

