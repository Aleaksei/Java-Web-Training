package com.epam.rental.service;

import com.epam.rental.DAO.DAOCreator;
import com.epam.rental.DAO.RentalPlaceDAO;
import com.epam.rental.builder.Builder;
import com.epam.rental.builder.BuilderFactory;
import com.epam.rental.builder.RentalPlaceBuilder;
import com.epam.rental.entities.RentalPlace;
import com.epam.rental.exception.ConnectionException;
import com.epam.rental.exception.DaoException;
import com.epam.rental.exception.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RentalPlaceService {


    public List<RentalPlace> getRentalPlace() throws ServiceException {
        try (DAOCreator creator = new DAOCreator()) {
            RentalPlaceDAO rentalPlaceDAO = creator.getRentalPlaceDAO();
            return rentalPlaceDAO.getRentalPlace();
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<RentalPlace> getRentalPlaceById(Long id) throws ServiceException {
        try (DAOCreator creator = new DAOCreator()) {
            RentalPlaceDAO rentalPlaceDAO = creator.getRentalPlaceDAO();
            return rentalPlaceDAO.getRentalPlaceById(id);
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(HashMap<String, String> map) throws ServiceException {
        try (DAOCreator daoCreator = new DAOCreator()) {
            RentalPlaceDAO rentalPlaceDAO = daoCreator.getRentalPlaceDAO();
            BuilderFactory factory = new BuilderFactory();
            Builder<RentalPlace> placeBuilder = factory.getBuilder("bike");
            RentalPlace place = ((RentalPlaceBuilder) placeBuilder).buildNew(map);

            rentalPlaceDAO.save(place);

        } catch (ConnectionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


}
