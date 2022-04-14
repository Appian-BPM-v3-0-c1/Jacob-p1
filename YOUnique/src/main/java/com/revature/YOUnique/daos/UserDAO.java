package com.revature.YOUnique.daos;

import com.revature.YOUnique.connection.DatabaseConnection;
import com.revature.YOUnique.models.Item;
import com.revature.YOUnique.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(User obj) {
        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (firstname, lastname, dateofbirth, email, username, password, address, city, state, zip, usertype) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getFirstName());
            ps.setString(2, obj.getLastName());
            ps.setString(3, obj.getDateOfBirth());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getUsername());
            ps.setString(6, obj.getPassword());
            ps.setString(7, obj.getAddress());
            ps.setString(8, obj.getCity());
            ps.setString(9, obj.getState());
            ps.setString(10, obj.getZip());
            ps.setInt(11, obj.getUserType());
            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastName"));
                user.setDateOfBirth(rs.getString("dateofbirth"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setUserType(rs.getInt("usertype"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastName"));
                user.setDateOfBirth(rs.getString("dateofbirth"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setUserType(rs.getInt("usertype"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAllById(int id) {
        return null;
    }

    @Override
    public boolean update(User updateObj) {
        return false;
    }

    @Override
    public boolean removeById(int usersId) {
        return false;
    }

    public List<String> findAllUsernames() {
        List<String> username_list = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                username_list.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username_list;
    }

    public User findByUsername(String username) {

        User user = new User();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastName"));
                user.setDateOfBirth(rs.getString("dateofbirth"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setZip(rs.getString("zip"));
                user.setUserType(rs.getInt("usertype"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

