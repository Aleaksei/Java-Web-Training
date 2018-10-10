package com.epam.rental.builder;

import com.epam.rental.entities.Bike;
import com.epam.rental.entities.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Builder<T extends Identifiable> {
    T build(ResultSet resultSet) throws SQLException;
}
