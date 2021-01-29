package com.jdbc_.role;

import com.jdbc_.main.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRepository {
    Connection connection = ConnectionProvider.getConnection();

    public void save(Role role) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into role(roleName) values (?)");
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Role get(String roleName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from role where roleName=?");
            preparedStatement.setString(1, roleName);
            ResultSet resultSet = preparedStatement.executeQuery();
            String rolename = null;
            String id = null;
            while (resultSet.next()) {
                rolename = resultSet.getString("roleName");
                id = resultSet.getString("id");
            }
            return new Role(rolename, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void getRoleTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from role");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String rolename = resultSet.getString("roleName");
                String id = resultSet.getString("id");
                System.out.println("id= " + id + "  roleName='" + rolename + "'");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(String oldRoleName, String updatedRoleName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update role set roleName=? where roleName=?");
            preparedStatement.setString(1, updatedRoleName);
            preparedStatement.setString(2, oldRoleName);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(String roleName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from role where roleName=?");
            preparedStatement.setString(1, roleName);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
