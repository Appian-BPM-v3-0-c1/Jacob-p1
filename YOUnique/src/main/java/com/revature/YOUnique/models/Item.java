package com.revature.YOUnique.models;

import java.text.DecimalFormat;

public class Item {
    DecimalFormat twoDForm = new DecimalFormat("#.00");
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock = 1;
    private int usersId;

    public Item() {
    }

    public Item(int id, String name, String description, double price, int stock, int usersId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.usersId = usersId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getUsersId() { return usersId; }

    public void setUsersId(int usersId) { this.usersId = usersId; }

    public String addItemToString() {
        return "Name: " + name
                + "\nDescription: " + description
                + "\nPrice: $" + price
                + "\nStock: " + stock;
    }

    public String toString() {
        return "\nName: " + name + "\nDescription: " + description + "\nPrice: $" + twoDForm.format(price) + " Stock: " + stock;
    }

    public String transactionToString() {
        return "Name: " + name + " Description: " + description + " Price: $" + twoDForm.format(price);
    }
}
