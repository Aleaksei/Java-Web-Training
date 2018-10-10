package com.epam.rental.service;

import com.epam.rental.DAO.AdminDAO;
import com.epam.rental.DAO.DAOCreator;
import com.epam.rental.DAO.UserDAOImpl;
import com.epam.rental.entities.User;
import com.epam.rental.entities.enums.UserRole;
import com.epam.rental.exception.ConnectionException;
import com.epam.rental.exception.ServiceException;

import java.util.Optional;

public class AbstractUserService {

    private UserRole role;

    public AbstractUserService(UserRole role){
        this.role = role;
    }

    public Optional<User> login(String login, String password) throws ServiceException {

        Optional<User> user = Optional.empty();

        try (DAOCreator daoCreator = new DAOCreator()) {
            switch (role) {
                case CLIENT:
                    UserDAOImpl userDAOImpl = daoCreator.getUserDao();
                    user = userDAOImpl.findUserByLoginAndPassword(login, password);
                    break;
                case ADMIN:
                    AdminDAO adminDAO = daoCreator.getAdminDAO();
                    user = adminDAO.findUserByLoginAndPassword(login, password);
                    break;
            }
            return user;
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage(), e);
        }


    }
}
