package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.entities.RentalPlace;
import com.epam.rental.exception.CommandException;
import com.epam.rental.exception.ServiceException;
import com.epam.rental.service.RentalPlaceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ForwardPlaceUpdateCommand implements Command {

    private final static String WEB_INF_ADMIN_UPDATE_PLACE_JSP = "/WEB-INF/admin/updatePlace.jsp";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        Optional<RentalPlace> place = Optional.empty();

        RentalPlaceService helperService = new RentalPlaceService();
        if (req.getParameter("id") != null) {
            Long id = Long.valueOf(req.getParameter("id"));
            try {
                place = helperService.getRentalPlaceById(id);
            } catch (ServiceException e) {
                throw new CommandException(e.getMessage(), e);
            }
        }

        if (place.isPresent()) {
            req.setAttribute("bike", place.get());
        }
        return WEB_INF_ADMIN_UPDATE_PLACE_JSP;
    }
}
