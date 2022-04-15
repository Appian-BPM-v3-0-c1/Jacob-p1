package com.revature.YOUnique.ui;

import com.revature.YOUnique.daos.*;
import com.revature.YOUnique.models.Cart;
import com.revature.YOUnique.models.Item;
import com.revature.YOUnique.models.User;
import com.revature.YOUnique.services.*;


import java.util.List;
import java.util.Scanner;

public class ItemMenu implements IMenu {
    private final Cart cart;
    private final User user;
    private final ItemService itemService;
    private final UserService userService;
    private final CartService cartService;

    Scanner scan = new Scanner(System.in);


    public ItemMenu(Cart cart, ItemService itemService, UserService userService, User user, CartService cartService) {
        this.cart = cart;
        this.itemService = itemService;
        this.userService = userService;
        this.user = user;
        this.cartService = cartService;

    }

    @Override
    public void start() {
        Item item;
        char input;
        exit:
        {
            while (true) {
                System.out.println("\n..........ITEMS..........");
                System.out.println("\n[1] View all items    [2] View my items    [3] Add item\n[4] View my cart    [x] Go back\n");
                System.out.print("Enter: ");

                input = scan.next().charAt(0);
                scan.nextLine();

                switch (input) {
                    case '1':
                        System.out.println("\nHere are some items on YOUnique\n");
                        viewAllItems();
                        break;
                    case '2':
                        findMyItems();
                        break;
                    case '3':
                        createItem();
                        break;
                    case '4':
                        new CartMenu(new ItemService(new ItemDAO()), new CartService(new CartDAO()), new TransactionService(new TransactionDAO()), new UserService(new UserDAO()), user).start();
                        break;
                    case 'x':
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void findMyItems() {
        Item item = new Item();
        char input;
        exit:
        {
            System.out.println("\n..........SELLING..........\n");

            List<Item> itemList = itemService.getItemDAO().findMyItems(user.getId());
            if (itemList.size() == 0) {
                System.out.println("\nYou have not added any items yet");
                break exit;
            } else {

                for (int i = 0; i < itemList.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + itemList.get(i).getName());
                }

                while (true) {
                    System.out.print("\nPick an item to view details or press [x] to go back: ");
                    input = scan.next().charAt(0);
                    if (input == 'x') {
                        break exit;
                    } else {
                        int number = Character.getNumericValue(input) - 1;
                        item = itemList.get(number);
                        System.out.println(item);
                        User seller = userService.getUserDAO().findById(itemList.get(number).getUsersId());
                        System.out.println("Location: " + seller.getCity() + ", " + seller.getState() + " " + seller.getZip());
                        break;
                    }
                }
                System.out.println("[r] Restock item    [x] Exit");
                while (true) {
                    input = scan.next().charAt(0);

                    switch (input) {
                        case 'r':
                            restock(item);
                            break exit;
                        case 'x':
                            break exit;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                }
            }
        }
    }

    private void createItem() {
        char input;
        boolean exit = false;

        Item item = new Item();

        while (!exit) {

            System.out.print("\nEnter in item name: ");

            item.setName(scan.nextLine());

            System.out.print("\nEnter in item description: ");
            item.setDescription(scan.nextLine());

            System.out.print("\nEnter in item price: ");
            item.setPrice(scan.nextDouble());

            item.setUsersId(user.getId());


            while (true) {
                System.out.println(item.addItemToString());
                System.out.println("\nIs this correct? (y/n)");
                System.out.print("\nEnter: ");

                input = scan.next().charAt(0);
                scan.nextLine();

                switch (input) {
                    case 'y' -> {
                        itemService.getItemDAO().save(item);
                        System.out.println("\nItem added successfully!\n");
                        exit = true;
                    }
                    case 'n' -> System.out.println("Let's start over\n");
                    default -> System.out.println("\nInvalid input!");
                }
                break;
            }
        }
    }

    private void viewAllItems() {
        Item item = new Item();
        boolean exit = false;
        char input;
        List<Item> itemList = itemService.getItemDAO().findAllInStock(user.getId());
        exit:{
            if(itemList.size() == 0){
                System.out.println("There are not any items added yet");
                break exit;
            } else {
                for (int i = 0; i < itemList.size(); i++) {
                    if (itemList.get(i).getStock() == 0) {
                        i++;
                    }
                    System.out.println("[" + (i + 1) + "] " + itemList.get(i).getName());
                }

                while (true) {
                    System.out.print("\nPick an item to view details or press [x] to go back: ");
                    input = scan.next().charAt(0);
                    if (input == 'x') {
                        break exit;
                    } else {
                        int number = Character.getNumericValue(input) - 1;
                        item = itemList.get(number);
                        System.out.println(item.toString());
                        User seller = userService.getUserDAO().findById(itemList.get(number).getUsersId());
                        System.out.println("Location: " + seller.getCity() + ", " + seller.getState() + " " + seller.getZip());
                        break;
                    }
                }
            }
            while (true) {
                System.out.println("\n[1] Add to cart    [x] Go back");

                input = scan.next().charAt(0);

                switch (input) {
                    case '1':
                        addToCart(item);
                        break exit;
                    case 'x':
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }

            }

        }
    }

    private void addToCart(Item item) {
        int count = 0;
        List<Cart> cartList = cartService.getCartDAO().viewCart(user.getId());
        for (Cart value : cartList) {
            if (value.getItemId() == item.getId()) {
                count++;
            }
        }
        if (count == 0) {
            Cart cart = new Cart();
            cart.setUsersId(user.getId());
            cart.setItemId(item.getId());
            System.out.println("\nSaved to cart");
            cartService.getCartDAO().save(cart);

        } else {
            System.out.println("That item is already in your cart");
        }
    }

    private void restock(Item item) {
        if (item.getStock() == 0) {
            itemService.getItemDAO().restock(item.getId());
            System.out.println("\n Your item is restocked");
        } else {
            System.out.println("You can only have one stock per item, hence the name YOUniqueQ!");
        }
    }
}

