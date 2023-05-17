package com.example.demo.model;

import java.util.List;

public class Cart {
    private int id;
    private List<Product> products;
    private double total;

    public Cart(int id, List<Product> products, double total) {
        this.id = id;
        this.products = products;
        this.total = total;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

