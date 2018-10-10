package com.epam.rental.DAO;

import com.epam.rental.DAO.api.BikeDAO;
import com.epam.rental.builder.Builder;
import com.epam.rental.builder.BuilderFactory;
import com.epam.rental.entities.Bike;
import com.epam.rental.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BikeDAOImpl extends AbstractDAO<Bike> implements BikeDAO {

    private static final String BIKE = "bike";
    private static final String QUERY = "select * from bike join biketype on bike.BikeType_id = biketype.id";
    private static final String Save = "INSERT INTO bike VALUE (?,?,?,?,?);";
    private static final String UPDATE = "UPDATE bike SET id = ?, Name=?, Cost= ?, BikeType_id= ?, RentalPlace_id= ? WHERE id = ?;";
    private static final int ID = 1;
    private static final int NAME = 2;
    private static final int COST = 3;
    private static final int BIKE_TYPE = 4;
    private static final int RENTAL_PLACE = 5;
    private static final int NUMBER_OF_PARAMETERS = 5;
    private static final int NOT_ID = -1;
    private Connection connection;

    public BikeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Bike> getById(Long id) {
        List<Bike> bikes = getAll();
        for (Bike bike :
                bikes) {
            if (Objects.equals(bike.getId(), id)) {
                return Optional.of(bike);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Bike> getAll() {
        BuilderFactory builderFactory = new BuilderFactory();
        Builder<Bike> builder = builderFactory.getBuilder(BIKE);
        return executeQuery(QUERY, builder);
    }

    @Override
    public void save(Bike item) throws DaoException {

        if (item.getId() == NOT_ID) {
            try {
                PreparedStatement statement = connection.prepareStatement(Save);
                statement.setObject(ID, null);
                statement.setObject(NAME, item.getName());
                statement.setObject(COST, item.getCost());
                statement.setObject(BIKE_TYPE, item.getBikeTypeId());
                statement.setObject(RENTAL_PLACE, item.getRentalPlaceId());

                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage(), e);
            }
        } else {
            try {
                PreparedStatement statement = connection.prepareStatement(UPDATE);
                statement.setObject(ID, item.getId());
                statement.setObject(NAME, item.getName());
                statement.setObject(COST, item.getCost());
                statement.setObject(BIKE_TYPE, item.getBikeTypeId());
                statement.setObject(RENTAL_PLACE, item.getRentalPlaceId());

                statement.setObject(ID + NUMBER_OF_PARAMETERS, item.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void remove(Long id) {
        super.remove(id);
    }

    @Override
    public String getTableName() {
        return BIKE;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

}
