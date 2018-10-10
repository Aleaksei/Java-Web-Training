package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.entities.Bike;
import com.epam.rental.entities.BikeType;
import com.epam.rental.entities.RentalPlace;
import com.epam.rental.exception.CommandException;
import com.epam.rental.exception.ServiceException;
import com.epam.rental.service.BikeService;
import com.epam.rental.service.BikeTypeService;
import com.epam.rental.service.RentalPlaceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ForwardBikeUpdateCommand implements Command {
    private static final String WEB_INF_ADMIN_UPDATE_BIKE_JSP = "/WEB-INF/admin/updateBike.jsp";
    private BikeTypeService helperService;
    private RentalPlaceService rentalPlaceService;

    public ForwardBikeUpdateCommand(BikeTypeService service, RentalPlaceService rentalPlaceService) {
        helperService = service;
        this.rentalPlaceService = rentalPlaceService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        Optional<Bike> bike = Optional.empty();

        BikeService bikeService = new BikeService();
        if (req.getParameter("id") != null) {
            Long id = Long.valueOf(req.getParameter("id"));
            try {
                bike = bikeService.getById(id);
            } catch (ServiceException e) {
                throw new CommandException(e.getMessage(), e);
            }
        }

        List<RentalPlace> places;
        List<BikeType> types;
        try {
            places = rentalPlaceService.getRentalPlace();
            types = helperService.getBikeType();
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }

        if (bike.isPresent()) {
            req.setAttribute("bike", bike.get());
        }
        req.setAttribute("places", places);
        req.setAttribute("types", types);
        return WEB_INF_ADMIN_UPDATE_BIKE_JSP;
    }
}
