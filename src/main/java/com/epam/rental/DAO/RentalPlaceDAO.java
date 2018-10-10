package com.epam.rental.DAO;

import com.epam.rental.builder.Builder;
import com.epam.rental.builder.BuilderFactory;
import com.epam.rental.entities.RentalPlace;
import com.epam.rental.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RentalPlaceDAO extends AbstractDAO<RentalPlace> {
    private static final String SELECT_FROM = "Select * from ";
    private final static String RENTAL_PLACE_TABLE = "rentalplace";
    private static final String Save = "INSERT INTO rentalplace VALUE (?,?,?);";
    private static final String UPDATE = "UPDATE rentalplace SET id = ?, Name=?, Adress= ? WHERE id = ?;";
    private static final int NOT_ID = -1;
    private static final int ID = 1;
    private static final int NAME = 2;
    private static final int ADDRESS = 3;
    private static final int NUMBER_OF_PARAMETERS = 3;
    private Connection connection;

    public RentalPlaceDAO(Connection connection) {
        this.connection = connection;
    }

    public List<RentalPlace> getRentalPlace() {
        BuilderFactory builderFactory = new BuilderFactory();
        Builder builder = builderFactory.getBuilder(RENTAL_PLACE_TABLE);
        String query = SELECT_FROM + RENTAL_PLACE_TABLE;
        return executeQuery(query, builder);
    }

    public Optional<RentalPlace> getRentalPlaceById(Long id) {
        List<RentalPlace> places = getRentalPlace();
        for (RentalPlace place : places) {
            if (Objects.equals(place.getId(), id)) {
                return Optional.of(place);
            }
        }
        return Optional.empty();
    }

    public void save(RentalPlace item) throws DaoException {
        if (item.getId() == NOT_ID) {
            try {
                PreparedStatement statement = connection.prepareStatement(Save);
                statement.setObject(ID, null);
                statement.setObject(NAME, item.getName());
                statement.setObject(ADDRESS, item.getAddress());

                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage(), e);
            }
        } else {
            try {
                PreparedStatement statement = connection.prepareStatement(UPDATE);
                statement.setObject(ID, item.getId());
                statement.setObject(NAME, item.getName());
                statement.setObject(ADDRESS, item.getAddress());

                statement.setObject(ID + NUMBER_OF_PARAMETERS, item.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
