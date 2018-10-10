package com.epam.rental.DAO.api;

import com.epam.rental.entities.User;

import java.util.Optional;

public interface UserDAO extends DAO<User> {
    Optional<User> findUserByLoginAndPassword (String login, String password);
}
