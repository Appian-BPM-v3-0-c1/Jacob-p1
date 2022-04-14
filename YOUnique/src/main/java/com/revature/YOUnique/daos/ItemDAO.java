package com.revature.YOUnique.daos;

import com.revature.YOUnique.connection.DatabaseConnection;
import com.revature.YOUnique.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements CrudDAO<Item> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(Item obj) {
        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO items (name, description, price, stock, users_id) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getDescription());
            ps.setDouble(3, obj.getPrice());
            ps.setInt(4, obj.getStock());
            ps.setInt(5, obj.getUsersId());

            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Item> findAll() {
        List<Item> itemList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM items");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getFloat("price"));
                item.setStock(rs.getInt("stock"));
                item.setUsersId(rs.getInt("users_id"));

                itemList.add(item);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item findById(int id) {
        return null;
    }

    @Override
    public List<Item> findAllById(int id) {
        List<Item> itemList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM items WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));

                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public boolean update(Item updateObj) {
        return false;
    }

    @Override
    public boolean removeById(int usersId) {
        return false;
    }


    public List<Item> findUserItems(int id) {
        List<Item> itemList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM items WHERE users_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();

                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));
                item.setUsersId(rs.getInt("users_id"));

                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
