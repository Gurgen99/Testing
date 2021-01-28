package com.jdbc_.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static final String url="jdbc:mysql://localhost:3306/test";
    private static final String username="root";
    private static final String password="root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
