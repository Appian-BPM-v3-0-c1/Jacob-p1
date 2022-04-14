package com.revature.YOUnique.ui;

import com.revature.YOUnique.daos.CartDAO;
import com.revature.YOUnique.daos.ItemDAO;
import com.revature.YOUnique.daos.TransactionDAO;
import com.revature.YOUnique.daos.UserDAO;
import com.revature.YOUnique.models.Cart;
import com.revature.YOUnique.models.Item;
import com.revature.YOUnique.models.User;
import com.revature.YOUnique.services.CartService;
import com.revature.YOUnique.services.ItemService;
import com.revature.YOUnique.services.TransactionService;
import com.revature.YOUnique.services.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class SellersMenu implements IMenu {
    private final User user;
    private final UserService userService;
    private final ItemService itemService;

    Scanner scan = new Scanner(System.in);

    public SellersMenu(User user, UserService userService, ItemService itemService) {
        this.user = user;
        this.userService = userService;
        this.itemService = itemService;
    }

    @Override
    public void start() {
        char input;
        exit:
        {
            while (true) {
                System.out.println("..........SELLERS..........");
                System.out.println("[1] View all sellers");
                System.out.println("[2] Search sellers by username");
                System.out.println("[x] Go back\n");

                input = scan.next().charAt(0);

                switch (input) {
                    case '1':
                        viewSellers();
                        break;
                    case '2':
                        searchSellers();
                        break;
                    case 'x':
                        break exit;

                    default:
                        System.out.println("\nInvalid input!");
                }
            }
        }
    }

    private void viewSellers() {
        User seller = new User();
        char input;
        exit:
        {
            List<User> userList = userService.getUserDAO().findAll();
            List<User> userList1 = new ArrayList<>(new HashSet<>(userList));
            for (int i = 0; i < userList1.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + userList1.get(i).getUsername());
                //System.out.println("[" + (i + 1) + "] " + userService.getUserDAO().findById(itemList.get(i).getUsersId()).getUsername());
            }

            while (true) {
                System.out.println("\nChoose a user to view their items:\n");
                input = scan.next().charAt(0);

                if (input == 'x') {
                    break exit;
                } else {
                    int number = Character.getNumericValue(input) - 1;
                    int sellerId = userList1.get(number).getId();
                    List<Item> itemList = itemService.getItemDAO().findUserItems(sellerId);
                    for (int i = 0; i < itemList.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + itemList.get(i));
                    }

                    System.out.println("\n[#] Add that item to cart");
                    System.out.println("[c] Go to cart");
                    System.out.println("[x] Go back");

                    input = scan.next().charAt(0);

                    if(input == 'c') {
                        new CartMenu(new ItemService(new ItemDAO()), new CartService(new CartDAO()), new TransactionService(new TransactionDAO()), new UserService(new UserDAO()), user).start();
                        break;
                    } else if(input == 'x'){
                        break exit;
                    } else {

                    }

                }
            }
        }
    }

    private void searchSellers() {
        System.out.println("\nType in either the whole username or just part of their username:");
        System.out.print("\nUsername: ");
        String input = scan.nextLine();

    }

    private void addToCart() {
        Cart cart = new Cart();
        while (true) {
            System.out.println("How many if this item would you like to add to your cart? (Max: " + item.getStock() + ")");
            int qty = scan.nextInt();
            scan.nextLine();

            if (qty <= item.getStock()) {
                double total = (item.getPrice()) * (qty);
                cart.setTotal(total);
                cart.setItemQty(qty);
                cart.setUsersId(user.getId());
                cart.setItemId(item.getId());
                System.out.println("Saved to cart");
                cartService.getCartDAO().save(cart);
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
