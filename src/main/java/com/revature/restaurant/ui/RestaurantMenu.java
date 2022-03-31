package com.revature.restaurant.ui;

import com.revature.restaurant.models.Restaruant;

import java.util.Scanner;

public class RestaurantMenu implements IMenu {

    @Override
    public void start() {
        char input = ' ';
        boolean exit = false;
        Scanner scan = new Scanner(System.in);

        while (!exit) {
            System.out.println("\nWelcome to restaurant menu!");
            System.out.println("[1] List all restaurants");
            System.out.println("[2] Search restaurant");
            System.out.println("[3] Create restaurant");
            System.out.println("[x] Exit");

            System.out.print("\nEnter: ");
            input = scan.next().charAt(0);

            switch (input) {
                case '1':
                    break;
                case '2':
                    break;
                case '3':
                    createRestaurant();
                    break;
                case 'x':
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid input!");
                    break;
            }
        }
    }

    private void createRestaurant() {
        char input = ' ';
        boolean exit = false;
        boolean confirm = false;
        Scanner scan = new Scanner(System.in);
        Restaruant restaurant = new Restaruant();

        while (!exit) {
            System.out.println("\nCreating restaurant...");
            System.out.print("Enter in restaurant id: ");
            restaurant.setId(scan.nextInt());

            System.out.print("\nEnter in restaurant name: ");
            restaurant.setName(scan.next().toLowerCase());

            System.out.print("\nEnter in restaurant city: ");
            restaurant.setCity(scan.next().toLowerCase());

            System.out.print("\nEnter in restaurant state: ");
            restaurant.setState(scan.next().toLowerCase());

            while (!confirm) {
                System.out.println("\nIs this correct? (y/n)\n");
                System.out.println(restaurant);

                input = scan.next().charAt(0);
                switch (input) {
                    case 'y':
                        System.out.println("\nRestaurant created successfully!");
                        exit = true;
                        confirm = true;
                        break;
                    case 'n':
                        confirm = true;
                        break;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }
}
