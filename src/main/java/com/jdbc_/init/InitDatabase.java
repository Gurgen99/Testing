package com.jdbc_.init;

import com.jdbc_.main.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDatabase {
    public static void initDatabase() {
        Connection connection = null;
        try {
            connection = ConnectionProvider.getConnection();
            Statement statement = connection.createStatement();
            dropTableRole(connection);
            dropTableUsers(connection);
            createUserTable(statement);
            createRoleTable(statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private static void createUserTable(Statement statement) throws SQLException {
        String query = "Create table if not exists Users(  " +
                "id int not null auto_increment primary key, " +
                "username varchar(30)," +
                "password varchar(256)," +
                "role_id varchar(256))";
        statement.executeUpdate(query);
    }

    private static void createRoleTable(Statement statement) throws SQLException {
        String query = "Create table if not exists Role(  " +
                "id int not null auto_increment primary key, " +
                "roleName varchar(256))";
        statement.executeUpdate(query);
    }

    private static void dropTableRole(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("drop table if exists role");
        preparedStatement.execute();
    }

    private static void dropTableUsers(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("drop table if exists users");
        preparedStatement.execute();
    }

}
