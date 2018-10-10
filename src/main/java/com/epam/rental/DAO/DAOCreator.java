package com.epam.rental.DAO;

import com.epam.rental.DAO.database.ConnectionPool;
import com.epam.rental.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class DAOCreator implements AutoCloseable{
    private static final Logger LOG = LogManager.getLogger(DAOCreator.class);
    private ConnectionPool connectionPool;
    private Connection connection;

    public DAOCreator() throws ConnectionException {
        connectionPool = ConnectionPool.getEntity();
        connection = connectionPool.getConnection();
        LOG.debug("Connection received");
    }

    public AdminDAO getAdminDAO() {
        return new AdminDAO(connection);
    }

    public UserDAOImpl getUserDao() {
        return new UserDAOImpl(connection);
    }

    public BikeDAOImpl getBikeDAO() {
        return new BikeDAOImpl(connection);
    }

    public RentalPlaceDAO getRentalPlaceDAO() {
        return new RentalPlaceDAO(connection);
    }

    public BikeTypeDAO getBikeTypeDAO(){
        return new BikeTypeDAO(connection);
    }

    @Override
    public void close() {
        connectionPool.closeConnection(connection);
        //LOG.debug("Connection closed");
    }

}