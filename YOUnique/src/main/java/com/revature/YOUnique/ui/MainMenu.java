package com.revature.YOUnique.ui;

import com.revature.YOUnique.daos.CartDAO;
import com.revature.YOUnique.daos.ItemDAO;
import com.revature.YOUnique.daos.TransactionDAO;
import com.revature.YOUnique.daos.UserDAO;
import com.revature.YOUnique.models.Cart;
import com.revature.YOUnique.models.User;
import com.revature.YOUnique.services.CartService;
import com.revature.YOUnique.services.ItemService;
import com.revature.YOUnique.services.TransactionService;
import com.revature.YOUnique.services.UserService;

import java.util.Scanner;

public class MainMenu implements IMenu {
    private final Cart cart;
    private final ItemService itemService;
    Scanner scan = new Scanner(System.in);
    private final User user;

    public MainMenu(Cart cart, ItemService itemService, User user) {
        this.cart = cart;
        this.itemService = itemService;
        this.user = user;
    }


    @Override
    public void start() {
        char input;

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to YOUnique " + user.getFirstName() + "!");
                System.out.println("\n[1] Go to Item Menu    [2] Go to Seller Menu    [3] View Transactions    [x] Log out\n");
                System.out.print("Enter: ");
                input = scan.next().charAt(0);

                switch (input) {
                    case '1':
                        new ItemMenu(new Cart(),new ItemService(new ItemDAO()),new UserService(new UserDAO()),user,new CartService(new CartDAO())).start();
                        break;
                    case '2':
                        new SellersMenu(user, new UserService(new UserDAO()), new ItemService(new ItemDAO()), new CartService(new CartDAO())).start();
                        break;
                    case '3':
                        new TransactionMenu(user, new UserService(new UserDAO()), new ItemService(new ItemDAO()),new CartService(new CartDAO()),new TransactionService(new TransactionDAO())).start();
                    break;
                        case 'x':
                        new LoginMenu(new UserService(new UserDAO())).start();
                        break exit;

                    default:
                        System.out.println("\nInvalid input!");
                }
            }
        }
    }
}
