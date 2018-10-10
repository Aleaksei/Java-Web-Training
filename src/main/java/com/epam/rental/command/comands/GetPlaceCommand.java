package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.entities.RentalPlace;
import com.epam.rental.exception.CommandException;
import com.epam.rental.exception.ServiceException;
import com.epam.rental.service.RentalPlaceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetPlaceCommand implements Command {
    private static final String WEB_INF_RENTAL_PLACE_JSP = "/WEB-INF/admin/rentalPlace.jsp";
    private static final String ERROR_JSP = "/WEB-INF/error.jsp";
    private static final String PLACES = "places";
    private RentalPlaceService service;

    public GetPlaceCommand(RentalPlaceService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page;
        List<RentalPlace> places = null;
        try {
            places = service.getRentalPlace();
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
        if (places.size() > 0){
            req.setAttribute(PLACES, places);
            page = WEB_INF_RENTAL_PLACE_JSP;
        } else {
            page = ERROR_JSP;
        }
        return page;
    }
}
