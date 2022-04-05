package com.revature.YOUnique.models;

public class Item {
    private String name;
    private String description;
    private double price;
    private double rating;
    private int qty = 1;

    public Item() {
    }

    public Item(String name, String description, double price, int qty, double rating) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.qty = qty;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Description: " + description + " Price: " + price + " Rating: " + rating + "/10";
    }

    public void AddItem() {

    }

    public void RemoveItem() {

    }
}
