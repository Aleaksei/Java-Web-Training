package com.epam.rental.DAO;

import com.epam.rental.builder.Builder;
import com.epam.rental.builder.BuilderFactory;
import com.epam.rental.entities.BikeType;

import java.sql.Connection;
import java.util.List;

public class BikeTypeDAO extends AbstractDAO<BikeType> {

    private static final String SELECT_FROM = "Select * from ";
    private final static String BIKE_TYPE_TABLE = "biketype";
    private Connection connection;

    public BikeTypeDAO(Connection connection){
        this.connection = connection;
    }

    public List getBikeType() {
        BuilderFactory builderFactory = new BuilderFactory();
        Builder builder = builderFactory.getBuilder(BIKE_TYPE_TABLE);
        String query = SELECT_FROM + BIKE_TYPE_TABLE;
        return executeQuery(query, builder);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}