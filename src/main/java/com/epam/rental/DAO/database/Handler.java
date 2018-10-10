package com.epam.rental.DAO.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Handler {

    private final static String LOGIN = "root";
    private final static String PASSWORD = "root";

    /*package*/ Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error");
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bikerental", LOGIN, PASSWORD);
    }
}
