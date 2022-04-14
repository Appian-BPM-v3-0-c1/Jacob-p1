package com.revature.YOUnique.services;

import com.revature.YOUnique.daos.ItemDAO;
public class ItemService {

    private final ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }
}
