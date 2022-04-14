package com.revature.YOUnique.models;

public class Transaction {

    private int id;
    private int cartId;
    private int usersId;

    public Transaction() {
    }

    public Transaction(int id, int itemId, int usersId) {
        this.id = id;
        this.cartId = cartId;
        this.usersId = usersId;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getItemId() { return cartId; }

    public void setItemId(int cartId) { this.cartId = cartId; }

    public int getUsersId() { return usersId; }

    public void setUsersId(int usersId) { this.usersId = usersId; }

}
