package com.epam.rental.builder;


public class BuilderFactory {

    private final static String ADMINISTRATOR_TABLE = "administrator";
    private final static String CLIENT_TABLE = "client";
    private final static String BIKE_TABLE = "bike";
    private final static String BIKE_TYPE_TABLE = "biketype";
    private final static String RENTAL_PLACE_TABLE = "rentalplace";

    public Builder getBuilder(String type) {

        switch (type) {
            case ADMINISTRATOR_TABLE:
                return new AdminBuilder();
            case CLIENT_TABLE:
                return new ClientBuilder();
            case BIKE_TABLE:
                return new BikeBuilder();
            case BIKE_TYPE_TABLE:
                return new BikeTypeBuilder();
            case RENTAL_PLACE_TABLE:
                return new RentalPlaceBuilder();
        }
        return null;
    }
}
