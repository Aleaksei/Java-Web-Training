package com.epam.rental.service;

import com.epam.rental.DAO.BikeDAOImpl;
import com.epam.rental.DAO.DAOCreator;
import com.epam.rental.builder.BikeBuilder;
import com.epam.rental.builder.Builder;
import com.epam.rental.builder.BuilderFactory;
import com.epam.rental.entities.Bike;
import com.epam.rental.exception.ConnectionException;
import com.epam.rental.exception.DaoException;
import com.epam.rental.exception.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class BikeService {

    public List<Bike> getAllBike() throws ServiceException {

        try (DAOCreator daoCreator = new DAOCreator()) {
            BikeDAOImpl bikeDAO = daoCreator.getBikeDAO();
            return bikeDAO.getAll();
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Bike> getById(Long id) throws ServiceException {

        try (DAOCreator daoCreator = new DAOCreator()) {
            BikeDAOImpl bikeDAO = daoCreator.getBikeDAO();

            return bikeDAO.getById(id);
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(HashMap<String, String> map) throws ServiceException {
        try (DAOCreator daoCreator = new DAOCreator()) {

            BikeDAOImpl bikeDAO = daoCreator.getBikeDAO();
            BuilderFactory factory = new BuilderFactory();
            Builder<Bike> bikeBuilder = factory.getBuilder("bike");
            Bike bike = ((BikeBuilder) bikeBuilder).buildNew(map);

            bikeDAO.save(bike);

        } catch (ConnectionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteBike(Long id) throws ServiceException {
        try (DAOCreator daoCreator = new DAOCreator()) {
            BikeDAOImpl bikeDAO = daoCreator.getBikeDAO();

            bikeDAO.remove(id);
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
