package com.revature.YOUnique.ui;


import com.revature.YOUnique.daos.ItemDAO;
import com.revature.YOUnique.models.Cart;
import com.revature.YOUnique.models.User;
import com.revature.YOUnique.services.ItemService;
import com.revature.YOUnique.services.UserService;

import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class LoginMenu implements IMenu {
    private final UserService userService;
    Scanner scan = new Scanner(System.in);



    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void start() {

        char input;
        exit : {


        while (true) {
            System.out.println("\nHello there, welcome to YOUnique!\n");
            System.out.println("[1] Login    [2] Create Account");
            input = scan.next().charAt(0);
            scan.nextLine();

            switch (input) {
                case '1':
                    existingUser();
                    break;
                case '2':
                    createAccount();
                    break;
                case 'x':
                    break exit;
                default:
                    System.out.println("Invalid input!");
                }
            }
        }
    }

    public void createAccount() {
        User user = new User();
        Date date = new Date();
        int year = date.getYear() + 1900;
        String firstName;
        String lastName;
        int birthYear;
        int month;
        int day;
        String email;
        String username;
        String password;
        String address;
        String city;
        String state;
        String zip;


        System.out.println("Since it's your first time, we need to gather some info from you...");
        while (true) {
            while (true) {
                System.out.print("\nFirst name: ");
                firstName = scan.nextLine();
                if (Character.isUpperCase(firstName.charAt(0)) && userService.isValidName(firstName)) {
                    user.setFirstName(firstName);
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }
            while (true) {
                System.out.print("\nLast name: ");
                lastName = scan.nextLine();
                if (Character.isUpperCase(lastName.charAt(0)) && userService.isValidName(lastName)) {
                    user.setLastName(lastName);
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }
            while (true) {
                System.out.println("\n[1] January [2] February [3] March [4] April");
                System.out.println("[5] May [6] June [7] July [8] August");
                System.out.println("[9] September [10] October [11] November [12] December");
                System.out.print("\nWhat month were you born in?: ");
                month = scan.nextInt();
                scan.nextLine();
                if (month >= 1 && month <= 12) {
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }

            while (true) {
                System.out.print("\nWhat day of that month were you born? ");
                day = scan.nextInt();
                scan.nextLine();
                if (day > 0 && day <= 31) {
                    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    } else if (month == 2 && day <= 29) {
                        if (year % 4 == 0) {
                        } else if ((!(year % 4 == 0)) && day <= 28) {
                        }
                    } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) {
                    }
                } else {
                    System.out.println("Invalid input!");
                }

                while (true) {
                    System.out.print("\nWhat year were you born? ");
                    birthYear = scan.nextInt();
                    scan.nextLine();
                    if (birthYear < year && birthYear > year - 122) {
                        break;
                    } else {
                        System.out.println("Invalid input!");
                    }
                }
                if (month == 2 && (!(birthYear % 4 == 0)) && day == 29) {
                    System.out.println("That birth date does not fall on a leap year.");

                } else {
                    if (month < 10 && day < 10) {
                        user.setDateOfBirth("0" + month + "/" + "0" + day + "/" + year);
                    } else if ((month < 10) && (!(day < 10))) {
                        user.setDateOfBirth("0" + month + "/" + day + "/" + year);
                    } else if (!(month < 10) && (day < 10)) {
                        user.setDateOfBirth(month + "/" + "0" + day + "/" + year);
                    } else if (!(month < 10 && day < 10)) {
                        user.setDateOfBirth("0" + month + "/" + day + "/" + year);
                    }
                    break;
                }
            }

            while (true) {
                System.out.print("\nPlease give your email address: ");
                email = scan.nextLine();
                if (userService.isValidEmail(email)) {
                    user.setEmail(email);
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }
            while (true) {
                System.out.print("\nAlright! Please make a unique username: ");
                username = scan.nextLine();
                if (!userService.isDuplicateUsername(username)) {
                    if (userService.isValidUsername(username)) {
                        user.setUsername(username);
                        break;
                    } else {
                        System.out.println("Invalid username..Your username:\n" +
                                "Must have at least one numeric character\n" +
                                "Must have at least one lowercase character\n" +
                                "Must have at least one uppercase character\n" +
                                "Username length should be between 8 and 20");
                    }
                } else {
                    System.out.println("Username is already taken...please try again");
                }
            }

            while (true) {
                System.out.print("\nAnd now a unique password: ");
                password = scan.nextLine();
                if (userService.isValidPassword(password)) {
                    user.setPassword(password);
                    break;
                } else {
                    System.out.println("Invalid password..Your password:\n" +
                            "Must have at least one numeric character.\n" +
                            "Must have at least one lowercase character\n" +
                            "Must have at least one uppercase character\n" +
                            "Must have at least one special symbol among !@#$%\n" +
                            "Password length should be between 8 and 20");
                }
            }

            while (true) {

                System.out.println("\nNext, we need your full address:");
                while (true) {
                    System.out.print("\nAddress (ex: 123 Passaic Avenue):");
                    address = scan.nextLine();
                    if (userService.isValidAddress(address)) {
                        char[] charArray = address.toCharArray();
                        boolean foundSpace = true;

                        for (int i = 0; i < charArray.length; i++) {
                            if (Character.isLetter(charArray[i])) {
                                if (foundSpace) {
                                    charArray[i] = Character.toUpperCase(charArray[i]);
                                    foundSpace = false;
                                }
                            } else {
                                foundSpace = true;
                            }
                        }
                        address = String.valueOf(charArray);
                        break;
                    } else {
                        System.out.println("Invalid input!");
                    }
                }
                while (true) {
                    System.out.print("\nCity (ex East Orange):");
                    city = scan.nextLine();
                    if (userService.isValidCity(city)) {
                        char[] charArray = city.toCharArray();
                        boolean foundSpace = true;

                        for (int i = 0; i < charArray.length; i++) {
                            if (Character.isLetter(charArray[i])) {
                                if (foundSpace) {
                                    charArray[i] = Character.toUpperCase(charArray[i]);
                                    foundSpace = false;
                                }
                            } else {
                                foundSpace = true;
                            }
                        }
                        city = String.valueOf(charArray);
                        break;
                    } else {
                        System.out.println("Invalid input!");
                    }
                }
                while (true) {
                    System.out.print("\nState (2 Letters ONLY 'ex NJ'):");
                    state = scan.nextLine().toUpperCase();
                    if (userService.isValidState(state)) {
                        break;
                    } else {
                        System.out.println("Invalid input!");
                    }
                }
                while (true) {
                    System.out.print("\nZip (5 numbers ONLY 'ex 01234'): ");
                    zip = scan.nextLine();
                    if (zip.matches("^[0-9]{5}$")) {
                        break;
                    } else {
                        System.out.println("Invalid input!");
                    }
                }

                user.setAddress(address);
                user.setCity(city);
                user.setState(state);
                user.setZip(zip);
                break;
            }
            System.out.println("\n" + user);
            System.out.print("\nDoes this look right to you? (y/n) ");

            while (true) {
                String choice = scan.next().toLowerCase();
                if (choice.equals("y")) {
                    userService.getUserDAO().save(user);
                    System.out.println("\nGREAT! We're all set " + user.getFirstName() + "!");
                    existingUser();
                    break;
                } else if (choice.equals("n")) {
                    System.out.println("\nLet's start over...\n");
                    break;
                } else {
                    System.out.println("Invalid input...please try again...");
                }
            }
        }
    }

    private void existingUser() {
        User user = new User();
        while (true) {
            System.out.print("\nUsername: ");
            user.setUsername(scan.next());

            System.out.print("\nPassword: ");
            user.setPassword((scan.next()));

            if (userService.isValidLogin(user)) {
                user = userService.getUserDAO().findByUsername(user.getUsername());
                new MainMenu(new Cart(),new ItemService(new ItemDAO()),user).start();
                break;
            } else {
                System.out.println("\nInvalid login");
            }
        }
    }

}
