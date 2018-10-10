package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.entities.Bike;
import com.epam.rental.entities.BikeType;
import com.epam.rental.exception.CommandException;
import com.epam.rental.exception.ServiceException;
import com.epam.rental.service.BikeService;
import com.epam.rental.service.BikeTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetBikeCommand implements Command {

    private static final String WEB_INF_BIKE_JSP = "/WEB-INF/admin/bike.jsp";
    private static final String ERROR_JSP = "/WEB-INF/error.jsp";
    private static final String BIKES = "bikes";
    public static final String TYPES = "types";
    private BikeService service;

    public GetBikeCommand(BikeService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page;
        BikeTypeService helperService = new BikeTypeService();
        try {
            List<BikeType> types = helperService.getBikeType();
            List<Bike> bikes = service.getAllBike();
            req.setAttribute(TYPES, types);
            if (bikes.size() > 0) {
                req.setAttribute(BIKES, bikes);
                page = WEB_INF_BIKE_JSP;
            } else {
                page = ERROR_JSP;
            }
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
