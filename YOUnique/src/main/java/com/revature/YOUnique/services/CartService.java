package com.revature.YOUnique.services;

import com.revature.YOUnique.daos.CartDAO;
public class CartService {

    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO) { this.cartDAO = cartDAO; }

    public CartDAO getCartDAO() { return cartDAO; }
}
