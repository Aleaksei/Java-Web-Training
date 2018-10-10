package com.epam.rental.builder;


import com.epam.rental.entities.Bike;
import com.epam.rental.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements Builder<User> {

    @Override
    public User build(ResultSet resultSet) throws SQLException {

        Long id;
        String login;
        String password;

        id = resultSet.getLong("id");
        login = resultSet.getNString("Login");
        password = resultSet.getString("Password");

        User user = new User(id, login, password);
        return user;
    }
}
