package com.epam.rental.builder;


import com.epam.rental.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminBuilder implements Builder<User> {

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String login  = resultSet.getNString("Login");
        String password = resultSet.getString("Password");

        return new User(id, login, password);
    }
}
