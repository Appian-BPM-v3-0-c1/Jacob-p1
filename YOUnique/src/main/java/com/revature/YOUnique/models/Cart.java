package com.revature.YOUnique.models;

public class Cart {
    private int id;
    private int itemQty;
    private double total;
    private int usersId;
    private int itemId;


    public Cart() {
    }

    public Cart(int id, int itemQty, double total, int usersId, int itemId) {
        this.id = id;
        this.itemQty = itemQty;
        this.total = total;
        this.usersId = usersId;
        this.itemId = itemId;

    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getItemQty() { return itemQty; }

    public void setItemQty(int itemQty) { this.itemQty = itemQty; }

    public double getTotal() { return total; }

    public void setTotal(double total) { this.total = total; }

    public int getUsersId() { return usersId; }

    public void setUsersId(int usersId) { this.usersId = usersId; }

    public int getItemId() { return itemId; }

    public void setItemId(int itemId) { this.itemId = itemId; }



    public String toString() {
            return "x " + itemQty;
    }
}

