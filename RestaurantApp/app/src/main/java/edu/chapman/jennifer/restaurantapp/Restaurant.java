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

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return (this.getName() + "  " + this.getPhone() + "  " + this.getWebsite() + "  " + this. getCategory()).toString();
    }
}

