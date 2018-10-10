package com.epam.rental.builder;


import com.epam.rental.entities.Bike;
import com.epam.rental.entities.BikeType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class BikeBuilder implements Builder<Bike>{
    private Long id;
    private String name;
    private int cost;
    private Long typeId;
    private int rentalPlaceId;

    @Override
    public Bike build(ResultSet resultSet) throws SQLException {

        id = resultSet.getLong("bike.id");
        name = resultSet.getNString("bike.Name");
        cost = resultSet.getInt("bike.Cost");
        typeId = resultSet.getLong("biketype.id");
        rentalPlaceId = resultSet.getInt("bike.RentalPlace_id");
        return new Bike(id, name, cost, typeId, rentalPlaceId);
    }

    public Bike buildNew(HashMap<String, String> map){

        id = Long.valueOf(map.get("id"));
        name = map.get("name");
        cost = Integer.parseInt(map.get("cost"));
        typeId = Long.valueOf(map.get("typeId"));
        rentalPlaceId = Integer.parseInt(map.get("rentalPlaceId"));

        return new Bike(id, name, cost, typeId, rentalPlaceId);
    }
}
