package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RentalBikeCommand implements Command {
    private static final String WEB_INF_CLIENT_RENTAL_BIKE_JSP = "/WEB-INF/client/rentalBike.jsp";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        return WEB_INF_CLIENT_RENTAL_BIKE_JSP;
    }
}
