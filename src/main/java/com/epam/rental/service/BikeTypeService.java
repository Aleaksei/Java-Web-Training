package com.epam.rental.service;

import com.epam.rental.DAO.BikeTypeDAO;
import com.epam.rental.DAO.DAOCreator;
import com.epam.rental.entities.BikeType;
import com.epam.rental.exception.ConnectionException;
import com.epam.rental.exception.ServiceException;

import java.util.List;

public class BikeTypeService {
    public List<BikeType> getBikeType() throws ServiceException {
        try (DAOCreator creator = new DAOCreator()) {
            BikeTypeDAO bikeTypeDAO = creator.getBikeTypeDAO();
            return bikeTypeDAO.getBikeType();
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
