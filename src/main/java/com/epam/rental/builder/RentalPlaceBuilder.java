package com.epam.rental.builder;

import com.epam.rental.entities.RentalPlace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RentalPlaceBuilder implements Builder<RentalPlace>{


    @Override
    public RentalPlace build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("rentalplace.id");
        String name = resultSet.getString("rentalplace.Name");
        String address = resultSet.getString("rentalplace.Adress");

        return new RentalPlace(id, name, address);
    }

    public RentalPlace buildNew(HashMap map) {

        Long id = (Long) map.get("id");
        String name = String.valueOf(map.get("name"));
        String address = String.valueOf(map.get("address"));

        return new RentalPlace(id, name, address);
    }
}
