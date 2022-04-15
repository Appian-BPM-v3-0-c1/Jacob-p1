package com.revature.YOUnique.services;

import com.revature.YOUnique.daos.UserDAO;
import com.revature.YOUnique.models.User;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public boolean isDuplicateUsername(String username) {
      List<String> username_list = userDAO.findAllUsernames();

        for (String u : username_list) {
            if (u.equals(username)) {
                return true;

            }
        }
        return false;
    }

    public boolean isValidUsername(String username) {
        return username.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@]).{8,20}$");
    }

    public boolean isValidLogin(User user) {
        List<User> userList = userDAO.findAll();

        for (User i : userList) {
            if (i.getUsername().equals(user.getUsername()) && i.getPassword().equals(user.getPassword())) {
                return true;
            }

        }
        return false;
    }

    public boolean isValidName(String firstName) {
        return firstName.matches("^[a-zA-Z]{1,100}$");
    }

    public boolean isValidEmail(String email) {
        return email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    public boolean isValidAddress(String address) {
        return address.matches("[\\d]+[A-Za-z0-9\\s,\\.]+");
    }

    public boolean isValidCity(String city) {
        return city.matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
    }

    public boolean isValidState(String state) {
        return state.matches("^[A-Z]{2}$");
    }

}
