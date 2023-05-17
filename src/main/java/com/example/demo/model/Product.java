package com.example.demo.model;

import java.util.List;

public class Product {
    private int id;
    private String title;
    private double rating;
    private List<String> images;

    public Product(int id, String title, double rating, List<String> images) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.images = images;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
