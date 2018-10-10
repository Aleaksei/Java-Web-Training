package com.epam.rental.entities;

public class Bike implements Identifiable{

    private Long id;
    private String name;
    private int cost;
    private Long bikeTypeId;
    private int rentalPlaceId;

    public Bike(Long id, String name, int cost, Long bikeTypeId, int rentalPlaceId){
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.bikeTypeId = bikeTypeId;
        this.rentalPlaceId = rentalPlaceId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Long getBikeTypeId() {
        return bikeTypeId;
    }

    public int getRentalPlaceId() {
        return rentalPlaceId;
    }


}
