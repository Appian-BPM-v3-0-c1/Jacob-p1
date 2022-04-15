package com.revature.YOUnique.daos;

import com.revature.YOUnique.connection.DatabaseConnection;
import com.revature.YOUnique.models.Cart;
import com.revature.YOUnique.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO implements CrudDAO<Cart> {
    Connection con = DatabaseConnection.getCon();


    @Override
    public int save(Cart obj) {
        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO carts (users_id, items_id) VALUES (?, ?)");
            ps.setInt(1, obj.getUsersId());
            ps.setInt(2, obj.getItemId());

            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Cart findById(int id) {
        return null;
    }

    @Override
    public List<Cart> findAllById(int id) {
        return null;
    }

    @Override
    public boolean update(Cart updateObj) {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    public List<Cart> viewCart(int usersId) {
        List<Cart> cartList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM carts WHERE users_id = ?");
            ps.setInt(1, usersId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUsersId(rs.getInt("users_id"));
                cart.setItemId(rs.getInt("items_id"));


                cartList.add(cart);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartList;
    }

    public boolean deleteFromCart(int usersId, int itemId) {
        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM carts WHERE users_id = ? AND items_id = ?");
            ps.setInt(1, usersId);
            ps.setInt(2, itemId);


            n = ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Cart findCartById(int id) {
        Cart cart = new Cart();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM carts WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cart.setId(rs.getInt("id"));
                cart.setUsersId(rs.getInt("users_id"));
                cart.setItemId(rs.getInt("items_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }
    public boolean soldItem(int itemId) {
        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("UPDATE items SET stock = 0 WHERE id = ?");
            ps.setInt(1, itemId);

            n = ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
