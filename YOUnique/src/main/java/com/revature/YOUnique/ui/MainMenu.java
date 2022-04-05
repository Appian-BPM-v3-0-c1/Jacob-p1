package com.revature.YOUnique.ui;

import java.util.Scanner;

public class MainMenu {
    UserLogin userLogin = new UserLogin();
    Scanner scan = new Scanner(System.in);

    public MainMenu() {

    }

    public void startMenu() {

        String selection;
        System.out.println("Hello there, GET USER'S FIRST NAME! Welcome to YOUnique!\n");
        System.out.println("[0] Login\n[1] Create Account");
        selection = scan.next();

        while(true) {
            if (selection.equals("0")) {
                userLogin.ExistingUser();
                break;
            } else if (selection.equals("1")) {
                userLogin.CreateAccount();
                break;
            } else {
                System.out.println("Invalid input...please try again...");
            }
        }


    }

}
