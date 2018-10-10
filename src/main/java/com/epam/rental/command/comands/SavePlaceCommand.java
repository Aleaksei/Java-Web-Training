package com.epam.rental.command.comands;

import com.epam.rental.command.Command;
import com.epam.rental.exception.CommandException;
import com.epam.rental.exception.ServiceException;
import com.epam.rental.service.RentalPlaceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class SavePlaceCommand implements Command {

    private RentalPlaceService service;

    public SavePlaceCommand(RentalPlaceService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        HashMap<String, String> map = new HashMap<>();
        map.put("id", req.getParameter("id"));
        map.put("name", req.getParameter("name"));
        map.put("address", req.getParameter("address"));

        try {
            service.save(map);
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }

        GetPlaceCommand placeCommand = new GetPlaceCommand(service);
        return placeCommand.execute(req, resp);
    }
}
