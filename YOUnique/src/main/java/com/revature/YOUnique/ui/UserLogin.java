package com.revature.YOUnique.ui;

import com.revature.YOUnique.models.User;

import java.util.Scanner;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLogin {
    User user = new User();
    Scanner scan = new Scanner(System.in);
    Date date = new Date();
    int year = date.getYear() + 1900;
    String username;
    String password;
    int birthYear;
    String firstName;
    String lastName;
    String regexn = "^[a-zA-Z]{1,100}$";
    String regexu = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    String regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{8,20}$";

    public UserLogin() {
    }

    public void CreateAccount() {
        String choice = "";
        String dateOfBirth = "";
        int month;
        int day;
        System.out.println("Since it's your first time, we need to gather some info from you...");
        while (!(choice.equals("y"))) {
            boolean validFirstName = false;
            boolean validLastName = false;
            while (true) {
                System.out.print("\nPlease give us your first name: ");
                firstName = scan.nextLine();
                validFirstName = FirstName(firstName, regexn);
                if (validFirstName) {
                    user.setFirstName(firstName);
                    break;
                } else {
                    System.out.println("Invalid input...please try again");
                }
            }
            while (true) {
                System.out.print("\nPlease give us your last name: ");
                lastName = scan.nextLine();
                validLastName = LastName(lastName, regexn);
                if (validLastName) {
                    user.setLastName(lastName);
                    break;
                } else {
                    System.out.println("Invalid input...please try again");
                }
            }
            while (true) {
                System.out.println("[1] January [2] February [3] March [4] April");
                System.out.println("[5] May [6] June [7] July [8] August");
                System.out.println("[9] September [10] October [11] November [12] December");
                System.out.print("\nWhat month were you born in?: ");
                month = scan.nextInt();
                if (month >= 1 && month <= 12) {
                    break;
                } else {
                    System.out.println("Invalid input...please try again...\n");
                }
            }

            while (true) {
                System.out.print("\nWhat day of that month were you born? ");
                day = scan.nextInt();
                if ((!(day < 0)) && day <= 31) {
                    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    } else if (month == 2 && day <= 29) {
                        if (year % 4 == 0) {
                        } else if ((!(year % 4 == 0)) && day <= 28) {
                        }
                    } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) {
                    }
                } else {
                    System.out.println("Invalid input...please enter a correct number");
                }

                while (true) {
                    System.out.print("\nWhat year were you born? ");
                    birthYear = scan.nextInt();
                    if (birthYear < year && birthYear > year - 122) {
                        break;
                    } else {
                        System.out.println("Invalid year...please try again");
                    }
                }
                if (month == 2 && (!(birthYear % 4 == 0)) && day == 29) {
                    System.out.println("That birth date does not fall on a leap year.");

                } else {
                    if (month < 10) {
                        user.setDateOfBirth("0" + month + "/" + day + "/" + year);
                    } else {
                        user.setDateOfBirth(month + "/" + day + "/" + year);
                    }
                    break;
                }
            }
            boolean validUsername = false;

            while (!validUsername) {
                System.out.print("\nAlright! Please make a unique username: ");
                username = scan.next();
                validUsername = UsernameCreator(username, regexu);
                if (validUsername) {
                    user.setUsername(username);
                } else {
                    System.out.println("Invalid username..Your username:\n" +
                            "Must have at least one numeric character\n" +
                            "Must have at least one lowercase character\n" +
                            "Must have at least one uppercase character\n" +
                            "Username length should be between 8 and 20");
                }
            }
            boolean validPassword = false;

            while (!validPassword) {
                System.out.print("\nAnd now a unique password: ");
                password = scan.next();
                if (password.equals(username)) {
                    System.out.println("Password cannot be the same as your username");
                } else {
                    String regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).{8,20}$";
                    validPassword = PasswordCreator(password, regexp);
                    if (validPassword) {
                        user.setPassword(password);
                    } else {
                        System.out.println("Invalid password..Your password:\n" +
                                "Must have at least one numeric character.\n" +
                                "Must have at least one lowercase character\n" +
                                "Must have at least one uppercase character\n" +
                                "Must have at least one special symbol among !@#$%\n" +
                                "Password length should be between 8 and 20");
                    }
                }
            }

            System.out.println(user.toString());
            System.out.print("\nDoes this look right to you? ");

            while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n"))) {
                choice = scan.next();
                if (choice.equals("y")) {
                    System.out.println("\nGREAT! We're all set " + user.getFirstName() + "!");
                    choice = "y";
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


    public void ExistingUser() {
        System.out.print("Username: ");

        System.out.print("Password: ");

    }

    public boolean FirstName(String firstName, String regexn) {
        Pattern pattern = Pattern.compile(regexn);
        Matcher matcher = pattern.matcher(firstName);
        return matcher.matches();
    }

    public boolean LastName(String lastName, String regexn) {
        Pattern pattern = Pattern.compile(regexn);
        Matcher matcher = pattern.matcher(lastName);
        return matcher.matches();
    }

    public boolean UsernameCreator(String username, String regexu) {
        Pattern pattern = Pattern.compile(regexu);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public boolean PasswordCreator(String password, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

