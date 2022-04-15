package com.revature.YOUnique.ui;

import com.revature.YOUnique.models.Cart;
import com.revature.YOUnique.models.Item;
import com.revature.YOUnique.models.Transaction;
import com.revature.YOUnique.models.User;
import com.revature.YOUnique.services.CartService;
import com.revature.YOUnique.services.ItemService;
import com.revature.YOUnique.services.TransactionService;
import com.revature.YOUnique.services.UserService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class TransactionMenu implements IMenu {
    DecimalFormat twoDForm = new DecimalFormat("#.00");
    Scanner scan = new Scanner(System.in);

    private final User user;
    private final UserService userService;
    private final ItemService itemService;
    private final CartService cartService;
    private final TransactionService transactionService;

    public TransactionMenu(User user, UserService userService, ItemService itemService, CartService cartService, TransactionService transactionService) {
        this.user = user;
        this.userService = userService;
        this.itemService = itemService;
        this.cartService = cartService;
        this.transactionService = transactionService;
    }

    @Override
    public void start() {
        char input;
        exit:
        {
            System.out.println("\n..........TRANSACTIONS..........\n");

            System.out.println("[1] Sort transactions by DATE ASCENDING");
            System.out.println("[2] Sort transactions by DATE DESCENDING");
            System.out.println("[x] Go back");

            input = scan.next().charAt(0);

            switch (input) {
                case '1':
                    List<Transaction> orderListAsc = transactionService.getTransactionDAO().sortDateAsc(user.getId());
                    if (orderListAsc.size() == 0) {
                        System.out.println("\nYou have not ordered any items yet\n");
                    } else {
                        for (int i = 0; i < orderListAsc.size(); i++) {
                            int itemId = orderListAsc.get(i).getItemId();
                            int usersId = orderListAsc.get(i).getUsersId();
                            String date = orderListAsc.get(i).getDate();
                            System.out.print("[" + (i + 1) + "] ");
                            System.out.print(date + " ");
                            Item item = itemService.getItemDAO().findItemById(itemId);
                            System.out.print(item.transactionToString());
                            User seller = userService.getUserDAO().findById(usersId);
                            System.out.println(" " + seller.transactionToString());
                        }
                    }
                    break exit;
                case '2':
                    List<Transaction> orderListDesc = transactionService.getTransactionDAO().sortDateDesc(user.getId());
                    if(orderListDesc.size() == 0) {
                        System.out.println("\nYou have not ordered any items yet\n");
                        break exit;
                    } else {
                        for (int i = 0; i < orderListDesc.size(); i++) {
                            int itemId = orderListDesc.get(i).getItemId();
                            int usersId = orderListDesc.get(i).getUsersId();
                            String date = orderListDesc.get(i).getDate();
                            System.out.print("[" + (i + 1) + "] ");
                            System.out.print(date + " ");
                            Item item = itemService.getItemDAO().findItemById(itemId);
                            System.out.print(item.transactionToString());
                            User seller = userService.getUserDAO().findById(usersId);
                            System.out.println(seller.transactionToString());
                        }
                    }
                    break exit;
                case 'x':
                    break exit;

                default:
                    System.out.println("\nInvalid input!");
            }
        }
    }
}
