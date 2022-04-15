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
    private final CartService cartService;

    Scanner scan = new Scanner(System.in);

    public SellersMenu(User user, UserService userService, ItemService itemService, CartService cartService) {
        this.user = user;
        this.userService = userService;
        this.itemService = itemService;
        this.cartService = cartService;
    }

    @Override
    public void start() {
        char input;
        exit:
        {
            while (true) {
                System.out.println("\n..........SELLERS..........\n");
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

                    if (itemList.size() == 0){
                        System.out.println("This user has not added items yet");
                        break exit;
                    } else {
                        for (int i = 0; i < itemList.size(); i++) {
                            System.out.println("[" + (i + 1) + "] " + itemList.get(i));
                        }

                        System.out.println("\n[#] Add that item to cart");
                        System.out.println("[c] Go to cart");
                        System.out.println("[x] Go back");

                        input = scan.next().charAt(0);

                        if (input == 'c') {
                            new CartMenu(new ItemService(new ItemDAO()), new CartService(new CartDAO()), new TransactionService(new TransactionDAO()), new UserService(new UserDAO()), user).start();
                            break;
                        } else if (input == 'x') {
                            break exit;
                        } else {
                            int number1 = Character.getNumericValue(input) - 1;
                            Item item = itemList.get(number1);
                            addToCart(item);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void searchSellers() {
        char input;
        List<User> userList;
        exit:
        {
            while (true) {
                System.out.println("\nType in either the whole username or just part of their username:");
                System.out.print("\nUsername: ");
                String name = scan.next();

                userList = userService.getUserDAO().searchSellers(name);

                if (userList.size() == 0) {
                    System.out.println("There are no users that match your search...going back");
                } else {
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + userList.get(i).getUsername());
                    }
                    break;
                }
            }
            while (true) {
                System.out.println("\nChoose a user to view their items:\n");
                input = scan.next().charAt(0);
                if (input == 'x') {
                    break exit;
                } else {
                    int number = Character.getNumericValue(input) - 1;
                    int sellerId = userList.get(number).getId();
                    List<Item> itemList = itemService.getItemDAO().findUserItems(sellerId);

                    if (itemList.size() == 0) {
                        System.out.println("This user has not added items yet");
                        break exit;
                    } else {
                        for (int i = 0; i < itemList.size(); i++) {
                            System.out.println("[" + (i + 1) + "] " + itemList.get(i));
                        }

                        System.out.println("\n[#] Add that item to cart");
                        System.out.println("[c] Go to cart");
                        System.out.println("[x] Go back");

                        input = scan.next().charAt(0);

                        if (input == 'c') {
                            new CartMenu(new ItemService(new ItemDAO()), new CartService(new CartDAO()), new TransactionService(new TransactionDAO()), new UserService(new UserDAO()), user).start();
                            break;
                        } else if (input == 'x') {
                            break exit;
                        } else {
                            int number1 = Character.getNumericValue(input) - 1;
                            Item item = itemList.get(number1);
                            addToCart(item);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void addToCart(Item item) {
        int count = 0;
        List<Cart> cartList = cartService.getCartDAO().viewCart(user.getId());
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getItemId() == item.getId()) {
                count++;
            }
            if (count == 0) {
                Cart cart = new Cart();
                cart.setUsersId(user.getId());
                cart.setItemId(item.getId());
                System.out.println("Saved to cart");
                cartService.getCartDAO().save(cart);
            } else {
                System.out.println("That item is already in your cart");
            }
        }
    }
}
