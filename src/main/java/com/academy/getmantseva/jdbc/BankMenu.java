package com.academy.getmantseva.jdbc;


import com.academy.getmantseva.jdbc.service.AccountService;
import com.academy.getmantseva.jdbc.service.TransactionService;
import com.academy.getmantseva.jdbc.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class BankMenu {
    public static void main(String[] args) throws SQLException {
        printMenu();
        start();
    }

    public static void printMenu() {
        System.out.println(
                "Menu:" + "\n"
                        + "1.Register a new user" + "\n"
                        + "2.Add an account for a new user" + "\n"
                        + "3.Top up an existing account" + "\n"
                        + "4.Withdraw funds from an existing account" + "\n"
        );
        System.out.print("Make your choice:");
    }

    public static void start() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();
        int userId;
        int numMenu = scanner.nextInt();
        switch (numMenu) {
            case 1:
                System.out.println("Register a new user");
                userService.registerNewUser();
                break;
            case 2:
                System.out.println("Enter your phone number to check if you are in the database:");
                userId = scanner.nextInt();
                if (userService.isUsersExists(userId)) {
                    accountService.addNewAccount();
                } else {
                    System.out.println("Your data is not in the database.Create a profile");
                }
                break;
            case 3:
                System.out.println("Enter your phone number to get information about the balance:");
                userId = scanner.nextInt();
                if (accountService.isAccountExists(userId)) {
                    accountService.getBalance(userId);
                    accountService.getCurrency(userId);
                    System.out.println("Enter the amount you want to deposit");
                    float topUpamount = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter in which currency:");
                    String currency = scanner.nextLine();
                    transactionService.addTransction(topUpamount);
                    accountService.replanishTheBalance(topUpamount, userId, currency);
                } else {
                    System.out.println("Your data is not in the database.Create a profile");
                }
                break;
            case 4:
                System.out.println("Enter your phone number to get information about the balance:");
                userId = scanner.nextInt();
                if (accountService.isAccountExists(userId)) {
                    accountService.getBalance(userId);
                    accountService.getCurrency(userId);
                    System.out.println("Enter the amount you want to withdraw");
                    float topUpamount = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter in which currency:");
                    String currency = scanner.nextLine();
                    transactionService.addTransction(topUpamount);
                    accountService.windrawCash(topUpamount, userId, currency);
                    System.out.println("Cash withdrawn");
                } else {
                    System.out.println("Your data is not in the database.Create a profile");
                }
                break;
            default:
                System.out.println("You entered the wrong selection command");
        }
    }
}
