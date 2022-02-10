package com.academy.getmantseva.jdbc.service;

import com.academy.getmantseva.jdbc.app.BankApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TransactionService {
    public void addTransction(float topUpamount) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter the transaction id:");
        int transactinId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("enter your account id:");
        int accountId = scanner.nextInt();

        try {
            connection = BankApplication.registerDriver();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Transactions (transactinId, accountId,amount) VALUES(?,?,?)");
            preparedStatement.setInt(1, transactinId);
            preparedStatement.setInt(2, accountId);
            preparedStatement.setFloat(3, topUpamount);

            int rows = preparedStatement.executeUpdate();
            System.out.printf("added to the table %d records", rows);
        } finally {
            BankApplication.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}

