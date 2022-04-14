package com.revature.YOUnique.ui;

import com.revature.YOUnique.models.Cart;
import com.revature.YOUnique.models.Item;
import com.revature.YOUnique.models.User;
import com.revature.YOUnique.services.CartService;
import com.revature.YOUnique.services.ItemService;

import java.util.List;

public class TransactionMenu implements IMenu {

    private final User user;
    private final ItemService itemService;
    private final CartService cartService;

    public TransactionMenu(User user, ItemService itemService, CartService cartService) {
        this.user = user;
        this.itemService = itemService;
        this.cartService = cartService;
    }

    @Override
    public void start() {
        System.out.println("\n..........TRANSACTIONS..........\n");
        System.out.println("[1] See all transactions");
        System.out.println("[]");
    }
}
