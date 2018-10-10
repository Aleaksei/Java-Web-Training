package com.epam.rental.command;


import com.epam.rental.command.comands.*;
import com.epam.rental.service.BikeService;
import com.epam.rental.service.BikeTypeService;
import com.epam.rental.service.RentalPlaceService;

public class CommandFactory {

    private static final String CLIENT_LOGIN = "ClientLogin";
    private static final String ADMIN_LOGIN = "AdminLogin";
    private static final String BIKE_MANAGER = "BikeManager";
    private static final String DELETE_BIKE = "deleteBike";
    private static final String SAVE_BIKE = "saveBike";
    private static final String FORWARD_BIKE_UPDATE = "forwardBikeUpdate";
    private static final String FORWARD_PLACE_UPDATE = "forwardPlaceUpdate";
    private static final String PLACE_MANAGER = "PlaceManager";
    private static final String CLIENT_MANAGER = "ClientManager";
    private static final String LOGOUT = "Logout";
    private static final String ADMIN_HOME = "AdminHome";
    private static final String CLIENT_HOME = "ClientHome";
    private static final String RENTAL_BIKE = "RentalBike";

    public static Command create(String command) {
        Command action = null;
        switch (command) {
            case CLIENT_LOGIN:
                action = new ClientLoginCommand();
                break;
            case ADMIN_LOGIN:
                action = new AdminLoginCommand();
                break;
            case LOGOUT:
                action = new LogoutCommand();
                break;
            case BIKE_MANAGER:
                action = new GetBikeCommand(new BikeService());
                break;
            case DELETE_BIKE:
                action = new DeleteBikeCommand(new BikeService());
                break;
            case FORWARD_BIKE_UPDATE:
                action = new ForwardBikeUpdateCommand(new BikeTypeService(),new RentalPlaceService());
                break;
            case FORWARD_PLACE_UPDATE:
                action = new ForwardPlaceUpdateCommand();
            case SAVE_BIKE:
                action = new SaveBikeCommand(new BikeService());
                break;
            case PLACE_MANAGER:
                action = new GetPlaceCommand(new RentalPlaceService());
                break;
            case CLIENT_MANAGER:
                action = new ClientManagerCommand();
                break;
            case ADMIN_HOME:
                action = new AdminHomeCommand();
                break;
            case CLIENT_HOME:
                action = new ClientHomeCommand();
                break;
            case RENTAL_BIKE:
                action = new RentalBikeCommand();
                break;
        }
        return action;
    }
}
