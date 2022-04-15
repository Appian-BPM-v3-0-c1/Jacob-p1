package com.revature.YOUnique.models;

public class Transaction {

    private int id;
    private String date;
    private int itemId;
    private int usersId;

    public Transaction() {
    }

    public Transaction(int id, String date, int itemId, int usersId) {
        this.id = id;
        this.date = date;
        this.itemId = itemId;
        this.usersId = usersId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }
}
