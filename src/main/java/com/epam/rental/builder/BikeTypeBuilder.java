package com.epam.rental.builder;


import com.epam.rental.entities.Bike;
import com.epam.rental.entities.BikeType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BikeTypeBuilder implements Builder<BikeType> {

    private Long id;
    private String name;

    public BikeType build(ResultSet resultSet) throws SQLException {

        id = resultSet.getLong("biketype.id");
        name = resultSet.getString("biketype.Name");

        return new BikeType(id, name);
    }
}
