package com.epam.rental.service;

import com.epam.rental.entities.User;
import com.epam.rental.entities.enums.UserRole;
import com.epam.rental.exception.ServiceException;

import java.util.Optional;

public class ClientService extends AbstractUserService {

    public ClientService() {
        super(UserRole.CLIENT);
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        return super.login(login, password);
    }

}
