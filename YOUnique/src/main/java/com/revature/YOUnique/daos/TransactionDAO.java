package com.revature.YOUnique.daos;

import com.revature.YOUnique.connection.DatabaseConnection;
import com.revature.YOUnique.models.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class TransactionDAO implements CrudDAO<Transaction>{
    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
    String dateTime = myDateObj.format(myFormatObj);
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(Transaction obj) {
        int n = 0;

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO transactions (date, items_id, users_id) VALUES (?, ?, ?)");
            ps.setString(1,dateTime);
            ps.setInt(2, obj.getItemId());
            ps.setInt(3, obj.getUsersId());


            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public Transaction findById(int id) {
        return null;
    }

    @Override
    public List<Transaction> findAllById(int id) {
        return null;
    }

    @Override
    public boolean update(Transaction updateObj) {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }
}
