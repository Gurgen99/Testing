package com.jdbc_.user;

import com.jdbc_.main.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    Connection connection = ConnectionProvider.getConnection();

    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into users(username, password) values (?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User get(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from users where userName=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            String id = null;
            String usernameResult = null;
            String password = null;
            String roleId = null;
            while (resultSet.next()) {
                id = resultSet.getString("id");
                usernameResult = resultSet.getString("username");
                password = resultSet.getString("password");
                roleId = resultSet.getString("role_id");
            }
            return new User(id, usernameResult,
                    password, roleId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try {
            String query = "Update users " +
                    "set username=?, password=?,role_id=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, String.valueOf(user.getRoleId()));
            preparedStatement.setString(4, String.valueOf(user.getId()));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where username=?");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}