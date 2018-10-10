package com.epam.rental.service;

import com.epam.rental.DAO.AdminDAO;
import com.epam.rental.DAO.DAOCreator;
import com.epam.rental.entities.User;
import com.epam.rental.entities.enums.UserRole;
import com.epam.rental.exception.ConnectionException;
import com.epam.rental.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class AdminService extends AbstractUserService {
    public AdminService() {
        super(UserRole.ADMIN);
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        return super.login(login, password);
    }

    public List<User> getAllClient() throws ServiceException {
        try (DAOCreator creator = new DAOCreator()) {
            AdminDAO adminDAO = creator.getAdminDAO();
            return adminDAO.getAll();
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
