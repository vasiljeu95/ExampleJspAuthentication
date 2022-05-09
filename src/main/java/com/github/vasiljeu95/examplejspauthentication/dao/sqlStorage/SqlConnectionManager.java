package com.github.vasiljeu95.examplejspauthentication.dao.sqlStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/webShop";
    private static final String USER = "root";
    private static final String PASSWORD = "root1234";
    private Connection connection;

    public Connection getConnection(){
        try {
            return connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
