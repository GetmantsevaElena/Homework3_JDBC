package com.academy.getmantseva.jdbc.service;

import com.academy.getmantseva.jdbc.app.BankApplication;
import com.academy.getmantseva.jdbc.model.User;

import java.sql.*;
import java.util.Scanner;

public class UserService {
    public void selectAllUsers() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = BankApplication.registerDriver();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT *FROM Users");
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("name"));
                user.setAddress(resultSet.getString("address"));
                System.out.println("UserId" + user.getUserId());
                System.out.println("name" + user.getName());
                System.out.println("address" + user.getAddress());
            }
        } finally {
            BankApplication.closeConnection(connection, statement, resultSet);
        }
    }

    public void registerNewUser() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your phone number:");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your address:");
        String address = scanner.nextLine();

        try {
            connection = BankApplication.registerDriver();
            preparedStatement = connection.prepareStatement("INSERT INTO Users(userId, name, address) VALUES(?,?,?)");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);

            int rows = preparedStatement.executeUpdate();
            System.out.printf("added to the table %d records", rows);
        } finally {
            BankApplication.closeConnection(connection, preparedStatement, resultSet);
        }
    }


    public boolean isUsersExists(int userId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM Users WHERE userId = " + userId;
            connection = BankApplication.registerDriver();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            } else {
                System.out.println("There is no user with this userId");
                return false;
            }
        } finally {
            BankApplication.closeConnection(connection, statement, resultSet);
        }
    }
}

