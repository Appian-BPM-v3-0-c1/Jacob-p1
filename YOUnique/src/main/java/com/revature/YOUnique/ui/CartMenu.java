package com.revature.YOUnique.ui;


import com.revature.YOUnique.models.Cart;
import com.revature.YOUnique.models.Item;
import com.revature.YOUnique.models.Transaction;
import com.revature.YOUnique.models.User;
import com.revature.YOUnique.services.CartService;
import com.revature.YOUnique.services.ItemService;
import com.revature.YOUnique.services.TransactionService;
import com.revature.YOUnique.services.UserService;


import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;

public class CartMenu implements IMenu {
    Scanner scan = new Scanner(System.in);
    DecimalFormat twoDForm = new DecimalFormat("#.00");
    char input;

    private final ItemService itemService;
    private final CartService cartService;
    private final TransactionService transactionService;
    private final UserService userService;
    private final User user;

    public CartMenu(ItemService itemService, CartService cartService, TransactionService transactionService, UserService userService, User user) {
        this.itemService = itemService;
        this.cartService = cartService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.user = user;
    }

    @Override
    public void start() {
        double price = 0;
        double qty = 0;
        double total = 0;

        exit : {
            while (true) {
                List<Item> itemList = itemService.getItemDAO().findAll();
                List<Cart> cartList = cartService.getCartDAO().viewCart(user.getId());
                if (cartList.size() > 0) {
                    for (int i = 0; i < cartList.size(); i++) {
                        System.out.print("\n[" + (i + 1) + "]" + itemService.getItemDAO().findAllById(cartList.get(i).getItemId()).toString().replace("[", "").replace("]", ""));
                        System.out.print(" x " + cartList.get(i).getItemQty() + " = ");
                        List<Item> item = itemService.getItemDAO().findAllById(cartList.get(i).getItemId());
                        price = item.get(0).getPrice();
                        qty = cartList.get(i).getItemQty();
                        total = price * qty;
                        System.out.println("$" + twoDForm.format(total));
                        User seller = userService.getUserDAO().findById(itemList.get(i).getUsersId());
                        System.out.println("Location: " + seller.getCity() + ", " + seller.getState() + " " + seller.getZip());


                    }
                } else {
                    System.out.println("No Items in the cart...going back to Items Menu\n");
                    break;
                }

                while (true) {
                    System.out.println("\n[1] Checkout    [2] Remove an item    [x] Go back");

                    input = scan.next().charAt(0);
                    switch (input) {
                        case '1':
                            for(int i = 0;i<cartList.size();i++) {
                            Transaction transaction = new Transaction();
                            transaction.setUsersId(user.getId());
                            transaction.setItemId(cartList.get(i).getItemId());
                            transactionService.getTransactionDAO().save(transaction);
                            }
                            break;
                        case '2':
                            System.out.println("Which item?");
                            input = scan.next().charAt(0);
                            int number = Character.getNumericValue(input) - 1;
                            remove(user.getId(), cartList.get(number).getItemId());
                            System.out.println("\nItem removed\n");
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
    }

    private void remove(int usersId, int itemId) {
        cartService.getCartDAO().deleteFromCart(usersId, itemId);
    }

}
