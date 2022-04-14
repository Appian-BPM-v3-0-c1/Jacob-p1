package com.revature.YOUnique;

import com.revature.YOUnique.ui.LoginMenu;
import com.revature.YOUnique.services.UserService;
import com.revature.YOUnique.daos.UserDAO;

public class Main {
    public static void main(String[] args) {
        new LoginMenu(new UserService(new UserDAO())).start();


    }
}
