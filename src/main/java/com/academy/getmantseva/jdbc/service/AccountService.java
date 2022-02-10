package com.academy.getmantseva.jdbc.service;

import com.academy.getmantseva.jdbc.app.BankApplication;
import com.academy.getmantseva.jdbc.model.Account;

import java.sql.*;
import java.util.Scanner;

public class AccountService {
    public void addNewAccount() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating an account for a new user");
        System.out.println("Enter your account id:");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the phone number again::");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("What amount do you want to deposit to the account?:");
        float balance = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("In what currency will you store?:");
        String currency = scanner.nextLine();

        try {
            connection = BankApplication.registerDriver();
            preparedStatement = connection.prepareStatement("INSERT INTO Accounts (accountId, userId, balance, currency) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setFloat(3, balance);
            preparedStatement.setString(4, currency);

            int rows = preparedStatement.executeUpdate();
            System.out.printf("added to the table %d records", rows);
        } finally {
            BankApplication.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    public boolean isAccountExists(int userId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM Accounts WHERE userId = " + userId;
            connection = BankApplication.registerDriver();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            } else {
                System.out.println("This user does not have an account");
                return false;
            }
        } finally {
            BankApplication.closeConnection(connection, statement, resultSet);
        }
    }

    public int getBalance(int userId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM Accounts WHERE userId = " + userId;
            connection = BankApplication.registerDriver();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int balance = 0;
            while (resultSet.next()) {
                balance = resultSet.getInt(3);
                System.out.println("balance" + balance);
            }
            return balance;
        } finally {
            BankApplication.closeConnection(connection, statement, resultSet);
        }
    }

    public String getCurrency(int userId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM Accounts WHERE userId = " + userId;
            connection = BankApplication.registerDriver();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            String currency = null;
            while (resultSet.next()) {
                currency = resultSet.getString(4);
                System.out.println("currency" + currency);
            }
            return currency;
        } finally {
            BankApplication.closeConnection(connection, statement, resultSet);
        }
    }

    public void replanishTheBalance(float topUpAmount, int userId, String currency) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = new Account();
        try {
            String sql = "UPDATE Accounts SET balance = balance + ? WHERE userId = ? AND currency = ?";
            connection = BankApplication.registerDriver();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, topUpAmount);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, currency);
            preparedStatement.executeUpdate();
            System.out.println("Account topped up");
        } finally {
            BankApplication.closeConnection1(connection, preparedStatement);
        }
    }

    public void windrawCash(float topUpAmount, int userId, String currency) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = new Account();
        try {
            String sql = "UPDATE Accounts SET balance = balance - ? WHERE userId = ? AND currency = ?";
            connection = BankApplication.registerDriver();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, topUpAmount);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, currency);
            preparedStatement.executeUpdate();
            System.out.println("You have withdrawn cash");
        } finally {
            BankApplication.closeConnection1(connection, preparedStatement);
        }
    }
}

