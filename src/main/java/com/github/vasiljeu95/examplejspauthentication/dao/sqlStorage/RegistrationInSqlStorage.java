package com.github.vasiljeu95.examplejspauthentication.dao.sqlStorage;

import com.github.vasiljeu95.examplejspauthentication.dao.RegisterStorage;
import com.github.vasiljeu95.examplejspauthentication.model.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationInSqlStorage implements RegisterStorage {
    public RegistrationInSqlStorage() {
    }

    @Override
    public void save(String login, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = new SqlConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users (login, password) values (?, ?, ?)");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, String.valueOf(UserRole.USER_ROLE));
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean find(String login) {
        boolean result = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = new SqlConnectionManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where login=?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            if (resultSet.next()) result = true;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}
