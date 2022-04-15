package com.revature.YOUnique.models;

public class Cart {
    private int id;
    private int usersId;
    private int itemId;


    public Cart() {
    }

    public Cart(int id, int usersId, int itemId) {
        this.id = id;
        this.usersId = usersId;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}

