package com.epam.rental.entities;

public class BikeType implements Identifiable {

    private Long id;
    private String Name;

    public BikeType(Long id, String name){
        this.id = id;
        this.Name = name;
    }

    public Long getId() {
        return id;
    }

    public String getTypeName() {
        return Name;
    }
}
